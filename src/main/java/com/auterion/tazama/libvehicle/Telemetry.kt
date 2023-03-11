package com.auterion.tazama.libvehicle

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class PositionAbsolute(
    val lat: Degrees = Degrees(),
    val lon: Degrees = Degrees(),
    val alt: Altitude = Altitude(0.0)
) : Measure<PositionAbsolute>() {
    override fun toMetric(): PositionAbsolute {
        return if (alt.measurementSystem == MeasurementSystem.METRIC) {
            this
        } else PositionAbsolute(lat, lon, alt.toMetric())
    }

    override fun toImperial(): PositionAbsolute {
        return if (alt.measurementSystem == MeasurementSystem.IMPERIAL) {
            this
        } else PositionAbsolute(lat, lon, alt.toImperial())
    }
}

class HomePosition(
    lat: Degrees = Degrees(),
    lon: Degrees = Degrees(),
    alt: Altitude = Altitude(0.0)
) : PositionAbsolute(lat, lon, alt)

data class HomeDistance(
    val horizontal: Distance = Distance(),
    val vertical: Altitude = Altitude()
) : Measure<HomeDistance>() {
    override fun toMetric(): HomeDistance {
        return if (horizontal.measurementSystem == MeasurementSystem.METRIC) {
            this
        } else HomeDistance(horizontal.toMetric(), vertical.toMetric())
    }

    override fun toImperial(): HomeDistance {
        return if (horizontal.measurementSystem == MeasurementSystem.IMPERIAL) {
            this
        } else HomeDistance(horizontal.toImperial(), vertical.toImperial())
    }
}

data class VelocityNed(
    val vx: Speed = Speed(),
    val vy: Speed = Speed(),
    val vz: Speed = Speed()
) : Measure<VelocityNed>() {
    override fun toMetric(): VelocityNed {
        return if (vx.measurementSystem == MeasurementSystem.METRIC) {
            this
        } else VelocityNed(vx.toMetric(), vy.toMetric(), vz.toMetric())
    }

    override fun toImperial(): VelocityNed {
        return if (vx.measurementSystem == MeasurementSystem.IMPERIAL) {
            this
        } else VelocityNed(vx.toImperial(), vy.toImperial(), vz.toImperial())
    }
}

data class Euler(
    val roll: Radian = Radian(),
    val pitch: Radian = Radian(),
    val yaw: Radian = Radian()
)

interface Telemetry {
    val position: StateFlow<PositionAbsolute?>
    val velocity: StateFlow<VelocityNed?>
    val attitude: StateFlow<Euler?>
    val homePosition: StateFlow<HomePosition?>
    val distanceToHome: StateFlow<HomeDistance?>
    val groundSpeed: StateFlow<Speed?>
}

interface TelemetryWriter {
    val positionWriter: MutableStateFlow<PositionAbsolute?>
    val velocityWriter: MutableStateFlow<VelocityNed?>
    val attitudeWriter: MutableStateFlow<Euler?>
    val homePositionWriter: MutableStateFlow<HomePosition?>
    val distanceToHomeWriter: MutableStateFlow<HomeDistance?>
    val groundSpeedWriter: MutableStateFlow<Speed?>
}

class TelemetryImpl : Telemetry, TelemetryWriter {
    override val positionWriter = MutableStateFlow<PositionAbsolute?>(null)
    override val position = positionWriter.asStateFlow()
    override val velocityWriter = MutableStateFlow<VelocityNed?>(null)
    override val velocity = velocityWriter.asStateFlow()
    override val attitudeWriter = MutableStateFlow<Euler?>(null)
    override val attitude = attitudeWriter.asStateFlow()
    override val homePositionWriter = MutableStateFlow<HomePosition?>(null)
    override val homePosition = homePositionWriter.asStateFlow()
    override val distanceToHomeWriter = MutableStateFlow<HomeDistance?>(null)
    override val distanceToHome = distanceToHomeWriter.asStateFlow()
    override val groundSpeedWriter = MutableStateFlow<Speed?>(null)
    override val groundSpeed = groundSpeedWriter.asStateFlow()
}
