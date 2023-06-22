/*
* This Source Code Form is subject to the terms of the Mozilla Public
* License, v. 2.0. If a copy of the MPL was not distributed with this
* file, You can obtain one at https://mozilla.org/MPL/2.0/.
*/

package com.auterion.tazama.libvehicle.util

import com.auterion.tazama.libvehicle.Degrees
import com.auterion.tazama.libvehicle.Distance
import com.auterion.tazama.libvehicle.PositionAbsolute
import com.auterion.tazama.libvehicle.Radian
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class GeoUtils {
    companion object {
        fun distanceBetween(lat1: Degrees, lon1: Degrees, lat2: Degrees, lon2: Degrees): Distance {
            val theta = lon1 - lon2
            val dist = sin(lat1.toRadian().value) * sin(lat2.toRadian().value) +
                    cos(lat1.toRadian().value) * cos(lat2.toRadian().value) * cos(theta.toRadian().value)

            val distRad = Radian(acos(dist))
            val distDeg = distRad.toDegrees()
            val distMeters = distDeg.value * 60.0 * 1.1515 * 1609.344

            return Distance(distMeters)
        }

        fun distanceBetween(left: PositionAbsolute, right: PositionAbsolute): Distance {
            return distanceBetween(left.lat, left.lon, right.lat, right.lon)
        }
    }
}