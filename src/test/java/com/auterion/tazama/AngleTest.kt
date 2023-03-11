package com.auterion.tazama

import com.auterion.tazama.libvehicle.Degrees
import com.auterion.tazama.libvehicle.Radian
import org.junit.Assert.assertEquals
import org.junit.Test

class AngleTest {
    @Test
    fun radian_defaultsToZero() {
        assertEquals(0.0, Radian().value, 0.0)
    }

    @Test
    fun radian_valueIsCorrect() {
        assertEquals(3.42, Radian(3.42).value, 0.0)
    }

    @Test
    fun radianFromDegrees_conversionIsCorrect() {
        assertEquals(0.0, Radian.fromDegrees().value, 0.0)
        assertEquals(0.0, Radian.fromDegrees(0.0).value, 0.0)
        assertEquals(0.6667158, Radian.fromDegrees(38.2).value, 0.000001)
        assertEquals(4.42, Radian.fromDegrees(253.2473).value, 0.000001)
        assertEquals(11.289, Radian.fromDegrees(646.8121).value, 0.000001)
    }

    @Test
    fun radianFromDegrees_worksWithNegatives() {
        assertEquals(-0.980875, Radian.fromDegrees(-56.2).value, 0.000001)
    }

    @Test
    fun radianToDegrees_conversionIsCorrect() {
        assertEquals(0.0, Radian().toDegrees().value, 0.0)
        assertEquals(0.0, Radian(0.0).toDegrees().value, 0.0)
        assertEquals(24.3, Radian(0.424115).toDegrees().value, 0.000001)
        assertEquals(212.0, Radian(3.700098).toDegrees().value, 0.000001)
        assertEquals(783.499731, Radian(13.67465).toDegrees().value, 0.000001)
    }

    @Test
    fun radianToDegrees_worksWithNegatives() {
        assertEquals(-74.484513, Radian(-1.3).toDegrees().value, 0.000001)
    }

    @Test
    fun degrees_defaultsToZero() {
        assertEquals(0.0, Degrees().value, 0.0)
    }

    @Test
    fun degrees_valueIsCorrect() {
        assertEquals(13.42, Degrees(13.42).value, 0.0)
    }

    @Test
    fun degreesToRadian_conversionIsCorrect() {
        assertEquals(0.0, Degrees().toRadian().value, 0.0)
        assertEquals(0.0, Degrees(0.0).toRadian().value, 0.0)
        assertEquals(0.424115, Degrees(24.3).toRadian().value, 0.000001)
        assertEquals(3.700098, Degrees(212.0).toRadian().value, 0.000001)
        assertEquals(13.67465, Degrees(783.499731).toRadian().value, 0.000001)
    }

    @Test
    fun degreesToRadian_worksWithNegatives() {
        assertEquals(-1.3, Degrees(-74.484513).toRadian().value, 0.000001)
    }

    @Test
    fun degreesMinusDegrees_isCorrect() {
        val left = Degrees(32.8)
        val right = Degrees(13.2)
        val expected = Degrees(19.6)

        val result = left - right

        assertEquals(expected.value, result.value, 0.000001)
    }

    @Test
    fun degreesTimesDegrees_isCorrect() {
        val left = Degrees(4.0)
        val right = Degrees(5.0)
        val expected = Degrees(20.0)

        val result = left * right

        assertEquals(expected, result)
    }
}
