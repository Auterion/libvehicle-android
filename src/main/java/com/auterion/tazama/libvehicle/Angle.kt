/*
* This Source Code Form is subject to the terms of the Mozilla Public
* License, v. 2.0. If a copy of the MPL was not distributed with this
* file, You can obtain one at https://mozilla.org/MPL/2.0/.
*/

package com.auterion.tazama.libvehicle

import kotlin.math.PI

interface Angle

data class Radian(val value: Double = 0.0) : Angle {
    companion object {
        fun fromDegrees(value_degrees: Double = 0.0): Radian {
            return Radian(value_degrees * PI / 180.0)
        }
    }

    fun toDegrees(): Degrees {
        return Degrees(value * 180.0 / PI)
    }
}

data class Degrees(val value: Double = 0.0) : Angle {
    fun toRadian(): Radian {
        return Radian(value * PI / 180.0)
    }

    operator fun minus(other: Degrees): Degrees {
        return Degrees(this.value - other.value)
    }

    operator fun times(other: Degrees): Degrees {
        return Degrees(this.value * other.value)
    }
}
