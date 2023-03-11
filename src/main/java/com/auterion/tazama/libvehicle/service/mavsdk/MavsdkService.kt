package com.auterion.tazama.libvehicle.service.mavsdk

import com.auterion.tazama.libvehicle.*
import com.auterion.tazama.libvehicle.service.VehicleService
import com.auterion.tazama.libvehicle.util.GeoUtils
import io.mavsdk.MavsdkEventQueue
import io.mavsdk.System
import io.mavsdk.mavsdkserver.MavsdkServer
import io.mavsdk.telemetry.Telemetry
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.pow
import kotlin.math.sqrt

class MavsdkService(private val vehicleWriter: VehicleWriter) : VehicleService {
    private lateinit var drone: System
    private val mavsdkServer = MavsdkServer()
    private val disposables = CopyOnWriteArrayList<Disposable>()

    override fun connect() {
        MavsdkEventQueue.executor().execute {
            drone = System("127.0.0.1", mavsdkServer.run())
            linkTelemetry(drone.telemetry, vehicleWriter.telemetryWriter)
            linkCamera(drone.camera, vehicleWriter.cameraWriter)
        }
    }

    private fun linkTelemetry(
        from: Telemetry,
        to: TelemetryWriter
    ) {
        linkPosition(from.position, to.positionWriter)
        linkVelocity(from.velocityNed, to.velocityWriter)
        linkAttitude(from.attitudeEuler, to.attitudeWriter)
        linkHomePosition(from.home, to.homePositionWriter)
        linkDistanceToHome(from.position, from.home, to.distanceToHomeWriter)
        linkGroundSpeed(from.velocityNed, to.groundSpeedWriter)
    }

    private fun linkPosition(
        from: Flowable<Telemetry.Position>,
        to: MutableStateFlow<PositionAbsolute?>
    ) {
        val positionDisposable = from.subscribe({ position ->
            to.value =
                PositionAbsolute(
                    Degrees(position.latitudeDeg),
                    Degrees(position.longitudeDeg),
                    Altitude(position.absoluteAltitudeM.toDouble())
                )
        }, {})

        disposables.add(positionDisposable)
    }

    private fun linkVelocity(
        from: Flowable<Telemetry.VelocityNed>,
        to: MutableStateFlow<VelocityNed?>
    ) {
        val velocityDisposable = from.subscribe({
            to.value = VelocityNed(
                Speed(it.northMS.toDouble()),
                Speed(it.eastMS.toDouble()),
                Speed(it.downMS.toDouble())
            )
        }, {})

        disposables.add(velocityDisposable)
    }

    private fun linkAttitude(
        from: Flowable<Telemetry.EulerAngle>,
        to: MutableStateFlow<Euler?>
    ) {
        val headingDisposable = from.subscribe({
            to.value = Euler(
                Radian.fromDegrees(it.rollDeg.toDouble()),
                Radian.fromDegrees(it.pitchDeg.toDouble()),
                Radian.fromDegrees(it.yawDeg.toDouble())
            )
        }, {})

        disposables.add(headingDisposable)
    }

    private fun linkHomePosition(
        from: Flowable<Telemetry.Position>,
        to: MutableStateFlow<HomePosition?>
    ) {
        val homeDisposable = from.subscribe({
            to.value = HomePosition(
                lat = Degrees(it.latitudeDeg),
                lon = Degrees(it.longitudeDeg),
                alt = Altitude(it.absoluteAltitudeM.toDouble())
            )
        }, {})

        disposables.add(homeDisposable)
    }

    private fun linkDistanceToHome(
        fromPos: Flowable<Telemetry.Position>,
        fromHome: Flowable<Telemetry.Position>,
        to: MutableStateFlow<HomeDistance?>
    ) {
        val distanceToHomeDisposable = Flowable.combineLatest(fromPos, fromHome) { position, home ->
            val horizontal =
                GeoUtils.distanceBetween(
                    Degrees(home.latitudeDeg),
                    Degrees(home.longitudeDeg),
                    Degrees(position.latitudeDeg),
                    Degrees(position.longitudeDeg)
                )
            val vertical =
                Altitude((position.absoluteAltitudeM - home.absoluteAltitudeM).toDouble())
            to.value = HomeDistance(horizontal, vertical)
        }.subscribe()

        disposables.add(distanceToHomeDisposable)
    }

    private fun linkGroundSpeed(
        from: Flowable<Telemetry.VelocityNed>,
        to: MutableStateFlow<Speed?>
    ) {
        val groundSpeedDisposable = from.subscribe({ velocity ->
            to.value = Speed(
                sqrt(
                    velocity.northMS.pow(2) + velocity.eastMS.pow(2)
                ).toDouble()
            )
        }, {})

        disposables.add(groundSpeedDisposable)
    }

    private fun linkCamera(
        from: io.mavsdk.camera.Camera,
        to: CameraWriter
    ) {
        linkVideoStreamInfo(from.videoStreamInfo, to.videoStreamInfoWriter)
    }

    private fun linkVideoStreamInfo(
        from: Flowable<io.mavsdk.camera.Camera.VideoStreamInfo>,
        to: MutableStateFlow<VideoStreamInfo?>
    ) {
        val videoStreamInfoDisposable = from.subscribe({ videoStreamInfo ->
            to.value = VideoStreamInfo(videoStreamInfo.settings.uri)
        }, {})

        disposables.add(videoStreamInfoDisposable)
    }

    override suspend fun destroy() {
        MavsdkEventQueue.executor().execute {
            disposables.forEach { it.dispose() }
            vehicleWriter.reset()
            mavsdkServer.stop()
            mavsdkServer.destroy()
        }
    }
}
