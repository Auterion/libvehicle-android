package com.auterion.tazama

import com.auterion.tazama.libvehicle.Altitude
import com.auterion.tazama.libvehicle.Degrees
import com.auterion.tazama.libvehicle.PositionAbsolute
import com.auterion.tazama.libvehicle.util.GeoUtils
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GeoTest {
    @Test
    fun distanceBetween_returnsZero() {
        val zeroValue = Degrees(0.0)
        val dist = GeoUtils.distanceBetween(zeroValue, zeroValue, zeroValue, zeroValue)
        assertEquals(dist.value, 0.0)
    }

    @Test
    fun distanceBetween_isCorrect() {
        val pos1 = PositionAbsolute(Degrees(), Degrees(), Altitude())
        val pos2 = PositionAbsolute(Degrees(1.0), Degrees(), Altitude())
        val dist = GeoUtils.distanceBetween(pos1.lat, pos1.lon, pos2.lat, pos2.lon)
        assertEquals(111189.0, dist.value, 1.0)
    }
}