package com.auterion.tazama.libvehicle

import kotlin.math.abs
import kotlin.math.round

const val METER_TO_FEET = 3.2808398950131

abstract class Measure<T : Measure<T>>(val measurementSystem: MeasurementSystem = MeasurementSystem.METRIC) {
    fun toSystem(system: MeasurementSystem): T {
        return when (system) {
            MeasurementSystem.METRIC -> toMetric()
            MeasurementSystem.IMPERIAL -> toImperial()
        }
    }

    abstract fun toMetric(): T
    abstract fun toImperial(): T

    enum class MeasurementSystem { METRIC, IMPERIAL }
}

class Speed(
    value: Double = 0.0,
    measurementSystem: MeasurementSystem = MeasurementSystem.METRIC
) : Measure<Speed>(measurementSystem) {
    val value = round(value * 1000) / 1000
    val unit: String
        get() = when (measurementSystem) {
            MeasurementSystem.METRIC -> "m/s"
            MeasurementSystem.IMPERIAL -> "ft/s"
        }

    override fun toMetric(): Speed {
        return when (measurementSystem) {
            MeasurementSystem.METRIC -> this
            else -> return Speed(value / METER_TO_FEET)
        }
    }

    override fun toImperial(): Speed {
        return when (measurementSystem) {
            MeasurementSystem.METRIC -> Speed(value * METER_TO_FEET, MeasurementSystem.IMPERIAL)
            MeasurementSystem.IMPERIAL -> this
        }
    }

    override fun equals(other: Any?) =
        other is Speed && other.measurementSystem == measurementSystem && other.value == value

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + measurementSystem.hashCode()
        return result
    }

    override fun toString() = "Speed: $value $unit"
}

class Distance(
    value: Double = 0.0,
    measurementSystem: MeasurementSystem = MeasurementSystem.METRIC
) : Measure<Distance>(measurementSystem) {
    val value = round(value * 1000) / 1000
    val unit: String
        get() = when (measurementSystem) {
            MeasurementSystem.METRIC -> "m"
            MeasurementSystem.IMPERIAL -> "ft"
        }

    override fun toMetric(): Distance {
        return when (measurementSystem) {
            MeasurementSystem.METRIC -> this
            MeasurementSystem.IMPERIAL -> Distance(value / METER_TO_FEET)
        }
    }

    override fun toImperial(): Distance {
        return when (measurementSystem) {
            MeasurementSystem.METRIC -> Distance(value * METER_TO_FEET, MeasurementSystem.IMPERIAL)
            MeasurementSystem.IMPERIAL -> this
        }
    }

    operator fun compareTo(other: Distance): Int {
        if ((this.value - other.value) > 0) {
            return 1
        } else if ((this.value - other.value) < 0) {
            return -1
        } else {
            return 0
        }
    }

    override fun equals(other: Any?) =
        other is Distance && other.measurementSystem == measurementSystem && other.value == value

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + measurementSystem.hashCode()
        return result
    }

    override fun toString() = "Distance: $value $unit"
}

class Altitude(
    value: Double = 0.0,
    measurementSystem: MeasurementSystem = MeasurementSystem.METRIC
) : Measure<Altitude>(measurementSystem) {
    val value = round(value * 1000) / 1000
    val unit: String
        get() = when (measurementSystem) {
            MeasurementSystem.METRIC -> "m"
            MeasurementSystem.IMPERIAL -> "ft"
        }

    override fun toMetric(): Altitude {
        return when (measurementSystem) {
            MeasurementSystem.METRIC -> this
            MeasurementSystem.IMPERIAL -> Altitude(value / METER_TO_FEET)
        }
    }

    override fun toImperial(): Altitude {
        return when (measurementSystem) {
            MeasurementSystem.METRIC -> Altitude(
                value * METER_TO_FEET,
                MeasurementSystem.IMPERIAL
            )
            MeasurementSystem.IMPERIAL -> this
        }
    }

    operator fun minus(other: Altitude): Altitude {
        if (other.measurementSystem != measurementSystem) {
            throw RuntimeException("Incompatible measurement system!")
        }

        return Altitude(this.value - other.value)
    }

    override fun equals(other: Any?): Boolean {
        return other is Altitude
                && other.measurementSystem == measurementSystem
                && (abs(other.value - value) < 0.0001)
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + measurementSystem.hashCode()
        return result
    }

    override fun toString() = "Altitude: $value $unit"
}
