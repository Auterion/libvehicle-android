package com.auterion.tazama.libvehicle.service.dummy

import com.auterion.tazama.libvehicle.Degrees
import com.auterion.tazama.libvehicle.Euler
import com.auterion.tazama.libvehicle.PositionAbsolute
import com.auterion.tazama.libvehicle.Radian
import com.auterion.tazama.libvehicle.Speed
import com.auterion.tazama.libvehicle.VehicleWriter
import com.auterion.tazama.libvehicle.VelocityNed
import com.auterion.tazama.libvehicle.VideoStreamInfo
import com.auterion.tazama.libvehicle.service.VehicleService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class DummyService(private val vehicleWriter: VehicleWriter) : VehicleService, CoroutineScope {
    override val coroutineContext: CoroutineContext = Job() + Dispatchers.IO

    private val emitJobs = CopyOnWriteArrayList<Job>()

    override fun connect() {
        emitJobs.add(launch { emitPosition() })
        emitJobs.add(launch { emitVelocity() })
        emitJobs.add(launch { emitAttitude() })
        emitJobs.add(launch { emitVideoStreamInfo() })
        emitJobs.add(launch { emitGroundSpeed() })
    }

    private tailrec suspend fun emitPosition() {
        if (!isActive) {
            return
        }

        println("Emitting dummy position")
        vehicleWriter.telemetryWriter.positionWriter.value =
            PositionAbsolute(
                Degrees(Random.nextDouble(3.0, 4.0)),
                Degrees(Random.nextDouble(46.0, 47.0))
            )

        delay(1000)
        emitPosition()
    }

    private tailrec suspend fun emitVelocity() {
        if (!isActive) {
            return
        }

        println("Emitting dummy velocity")
        vehicleWriter.telemetryWriter.velocityWriter.value =
            VelocityNed(Speed(1.0), Speed(0.0), Speed(0.0))

        delay(1000)
        emitVelocity()
    }

    private tailrec suspend fun emitGroundSpeed() {
        if (!isActive) {
            return
        }

        vehicleWriter.telemetryWriter.groundSpeedWriter.value = Speed(1.0)
        delay(1000)
        emitGroundSpeed()
    }

    private tailrec suspend fun emitAttitude() {
        if (!isActive) {
            return
        }

        println("Emitting dummy attitude")
        vehicleWriter.telemetryWriter.attitudeWriter.value =
            Euler(Radian(0.0), Radian(0.0), Radian(0.0))

        delay(1000)
        emitAttitude()
    }

    private tailrec suspend fun emitVideoStreamInfo() {
        if (!isActive) {
            return
        }

        println("Emitting dummy videoStreamInfo")
        vehicleWriter.cameraWriter.videoStreamInfoWriter.value =
            VideoStreamInfo("rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mp4")

        delay(1000)
        emitVideoStreamInfo()
    }

    override suspend fun destroy() {
        emitJobs.forEach { it.cancelAndJoin() }
        vehicleWriter.reset()
    }
}
