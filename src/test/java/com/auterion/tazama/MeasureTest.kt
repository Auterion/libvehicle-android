package com.auterion.tazama

import com.auterion.tazama.libvehicle.Altitude
import com.auterion.tazama.libvehicle.Distance
import com.auterion.tazama.libvehicle.Measure.MeasurementSystem.IMPERIAL
import com.auterion.tazama.libvehicle.Measure.MeasurementSystem.METRIC
import com.auterion.tazama.libvehicle.Speed
import org.junit.Assert.*
import org.junit.Test

class MeasureTest {
    @Test
    fun speed_defaultsToZero() {
        assertEquals(0.0, Speed().value, 0.0)
    }

    @Test
    fun speed_isCorrect() {
        assertEquals(24.0, Speed(24.0).value, 0.0)
    }

    @Test
    fun speed_defaultsToMetric() {
        assertEquals(METRIC, Speed().measurementSystem)
    }

    @Test
    fun speed_convertsToImperial() {
        val speedMetric = Speed(42.24)

        val speedImperial = speedMetric.toImperial()

        assertEquals(138.583, speedImperial.value, 0.0001)
    }

    @Test
    fun speed_constructsAsImperial() {
        assertEquals(IMPERIAL, Speed(measurementSystem = IMPERIAL).measurementSystem)
    }

    @Test
    fun speed_convertsToMetric() {
        val speedImperial = Speed(120.6, IMPERIAL)

        val speedMetric = speedImperial.toMetric()

        assertEquals(36.759, speedMetric.value, 0.0001)
    }

    @Test
    fun speed_convertsToImperialAndBack() {
        val speedMetric = Speed(12.3)

        val speedImperial = speedMetric.toImperial()
        val speedMetricBack = speedImperial.toMetric()

        assertEquals(speedMetric, speedMetricBack)
    }

    @Test
    fun speed_unitIsCorrectInMetric() {
        assertEquals("m/s", Speed().unit)
    }

    @Test
    fun speed_unitIsCorrectInImperial() {
        assertEquals("ft/s", Speed(measurementSystem = IMPERIAL).unit)
    }

    @Test
    fun speed_equalsOperatorIsCorrect() {
        val speedMetric1 = Speed(23.2)
        val speedMetric2 = Speed(23.2)
        val speedMetric3 = Speed(43.1)
        assertEquals(speedMetric1, speedMetric2)
        assertNotEquals(speedMetric1, speedMetric3)

        val speedImperial1 = Speed(43.1, IMPERIAL)
        val speedImperial2 = Speed(43.1, IMPERIAL)
        val speedImperial3 = Speed(23.2, IMPERIAL)
        assertEquals(speedImperial1, speedImperial2)
        assertNotEquals(speedImperial1, speedImperial3)

        assertNotEquals(speedMetric1, speedImperial1)
        assertNotEquals(speedMetric1, speedImperial3)
    }

    @Test
    fun speed_hashcodeIsCorrect() {
        val speedMetric1 = Speed(23.2)
        val speedMetric2 = Speed(23.2)
        val speedMetric3 = Speed(43.1)
        assertEquals(speedMetric1.hashCode(), speedMetric2.hashCode())
        assertNotEquals(speedMetric1.hashCode(), speedMetric3.hashCode())

        val speedImperial1 = Speed(43.1, IMPERIAL)
        val speedImperial2 = Speed(43.1, IMPERIAL)
        val speedImperial3 = Speed(23.2, IMPERIAL)
        assertEquals(speedImperial1.hashCode(), speedImperial2.hashCode())
        assertNotEquals(speedImperial1.hashCode(), speedImperial3.hashCode())

        assertNotEquals(speedMetric1.hashCode(), speedImperial1.hashCode())
        assertNotEquals(speedMetric1.hashCode(), speedImperial3.hashCode())
    }

    @Test
    fun distance_defaultsToZero() {
        assertEquals(0.0, Distance().value, 0.0)
    }

    @Test
    fun distance_isCorrect() {
        assertEquals(24.0, Distance(24.0).value, 0.0)
    }

    @Test
    fun distance_defaultsToMetric() {
        assertEquals(METRIC, Distance().measurementSystem)
    }

    @Test
    fun distance_convertsToImperial() {
        val distanceMetric = Distance(42.24)

        val distanceImperial = distanceMetric.toImperial()

        assertEquals(138.583, distanceImperial.value, 0.0001)
    }

    @Test
    fun distance_constructsAsImperial() {
        assertEquals(IMPERIAL, Distance(measurementSystem = IMPERIAL).measurementSystem)
    }

    @Test
    fun distance_convertsToMetric() {
        val distanceImperial = Distance(120.6, IMPERIAL)

        val distanceMetric = distanceImperial.toMetric()

        assertEquals(36.759, distanceMetric.value, 0.0001)
    }

    @Test
    fun distance_convertsToImperialAndBack() {
        val distanceMetric = Distance(12.3)

        val distanceImperial = distanceMetric.toImperial()
        val distanceMetricBack = distanceImperial.toMetric()

        assertEquals(distanceMetric, distanceMetricBack)
    }

    @Test
    fun distance_unitIsCorrectInMetric() {
        assertEquals("m", Distance().unit)
    }

    @Test
    fun distance_unitIsCorrectInImperial() {
        assertEquals("ft", Distance(measurementSystem = IMPERIAL).unit)
    }

    @Test
    fun distance_compareToIsCorrect() {
        assertTrue(Distance(22.0) < Distance(24.2))
        assertTrue(Distance(12.0) > Distance(3.8))
        assertTrue(Distance(13.2) > Distance(-42.8))

        assertFalse(Distance(0.0) < Distance(0.0))
        assertFalse(Distance(0.0) > Distance(0.0))
    }

    @Test
    fun distance_equalsOperatorIsCorrect() {
        val distanceMetric1 = Distance(23.2)
        val distanceMetric2 = Distance(23.2)
        val distanceMetric3 = Distance(43.1)
        assertEquals(distanceMetric1, distanceMetric2)
        assertNotEquals(distanceMetric1, distanceMetric3)

        val distanceImperial1 = Distance(43.1, IMPERIAL)
        val distanceImperial2 = Distance(43.1, IMPERIAL)
        val distanceImperial3 = Distance(23.2, IMPERIAL)
        assertEquals(distanceImperial1, distanceImperial2)
        assertNotEquals(distanceImperial1, distanceImperial3)

        assertNotEquals(distanceMetric1, distanceImperial1)
        assertNotEquals(distanceMetric1, distanceImperial3)
    }

    @Test
    fun distance_hashcodeIsCorrect() {
        val distanceMetric1 = Distance(23.2)
        val distanceMetric2 = Distance(23.2)
        val distanceMetric3 = Distance(43.1)
        assertEquals(distanceMetric1.hashCode(), distanceMetric2.hashCode())
        assertNotEquals(distanceMetric1.hashCode(), distanceMetric3.hashCode())

        val distanceImperial1 = Distance(43.1, IMPERIAL)
        val distanceImperial2 = Distance(43.1, IMPERIAL)
        val distanceImperial3 = Distance(23.2, IMPERIAL)
        assertEquals(distanceImperial1.hashCode(), distanceImperial2.hashCode())
        assertNotEquals(distanceImperial1.hashCode(), distanceImperial3.hashCode())

        assertNotEquals(distanceMetric1.hashCode(), distanceImperial1.hashCode())
        assertNotEquals(distanceMetric1.hashCode(), distanceImperial3.hashCode())
    }

    @Test
    fun altitude_defaultsToZero() {
        assertEquals(0.0, Altitude().value, 0.0)
    }

    @Test
    fun altitude_isCorrect() {
        assertEquals(24.0, Altitude(24.0).value, 0.0)
    }

    @Test
    fun altitude_defaultsToMetric() {
        assertEquals(METRIC, Altitude().measurementSystem)
    }

    @Test
    fun altitude_convertsToImperial() {
        val altitudeMetric = Altitude(42.24)

        val altitudeImperial = altitudeMetric.toImperial()

        assertEquals(138.583, altitudeImperial.value, 0.0001)
    }

    @Test
    fun altitude_constructsAsImperial() {
        assertEquals(IMPERIAL, Altitude(measurementSystem = IMPERIAL).measurementSystem)
    }

    @Test
    fun altitude_convertsToMetric() {
        val altitudeImperial = Altitude(120.6, IMPERIAL)
        val altitudeExpected = Altitude(36.759, METRIC)

        val altitudeMetric = altitudeImperial.toMetric()

        assertEquals(altitudeExpected, altitudeMetric)
    }

    @Test
    fun altitude_convertsToImperialAndBack() {
        val altitudeMetric = Altitude(12.3)

        val altitudeImperial = altitudeMetric.toImperial()
        val altitudeMetricBack = altitudeImperial.toMetric()

        assertEquals(altitudeMetric, altitudeMetricBack)
    }

    @Test
    fun altitude_unitIsCorrectInMetric() {
        assertEquals("m", Altitude().unit)
    }

    @Test
    fun altitude_unitIsCorrectInImperial() {
        assertEquals("ft", Altitude(measurementSystem = IMPERIAL).unit)
    }

    @Test
    fun altitude_equalsOperatorIsCorrect() {
        val altitudeMetric1 = Altitude(23.2)
        val altitudeMetric2 = Altitude(23.2)
        val altitudeMetric3 = Altitude(43.1)
        assertEquals(altitudeMetric1, altitudeMetric2)
        assertNotEquals(altitudeMetric1, altitudeMetric3)

        val altitudeImperial1 = Altitude(43.1, IMPERIAL)
        val altitudeImperial2 = Altitude(43.1, IMPERIAL)
        val altitudeImperial3 = Altitude(23.2, IMPERIAL)
        assertEquals(altitudeImperial1, altitudeImperial2)
        assertNotEquals(altitudeImperial1, altitudeImperial3)

        assertNotEquals(altitudeMetric1, altitudeImperial1)
        assertNotEquals(altitudeMetric1, altitudeImperial3)
    }

    @Test
    fun altitude_hashcodeIsCorrect() {
        val altitudeMetric1 = Altitude(23.2)
        val altitudeMetric2 = Altitude(23.2)
        val altitudeMetric3 = Altitude(43.1)
        assertEquals(altitudeMetric1.hashCode(), altitudeMetric2.hashCode())
        assertNotEquals(altitudeMetric1.hashCode(), altitudeMetric3.hashCode())

        val altitudeImperial1 = Altitude(43.1, IMPERIAL)
        val altitudeImperial2 = Altitude(43.1, IMPERIAL)
        val altitudeImperial3 = Altitude(23.2, IMPERIAL)
        assertEquals(altitudeImperial1.hashCode(), altitudeImperial2.hashCode())
        assertNotEquals(altitudeImperial1.hashCode(), altitudeImperial3.hashCode())

        assertNotEquals(altitudeMetric1.hashCode(), altitudeImperial1.hashCode())
        assertNotEquals(altitudeMetric1.hashCode(), altitudeImperial3.hashCode())
    }

    @Test
    fun altitude_minusOperatorIsCorrect() {
        val altitudeLeft = Altitude(40.2)
        val altitudeRight = Altitude(18.4)
        val altitudeExpected = Altitude(21.8)

        val altitudeDiff = altitudeLeft - altitudeRight

        assertEquals(altitudeExpected, altitudeDiff)
    }

    @Test
    fun altitude_minusThrowsIfMeasurementSystemDiffers() {
        val altitudeLeft = Altitude(40.2)
        val altitudeRight = Altitude(18.4, IMPERIAL)

        assertThrows(RuntimeException::class.java) {
            altitudeLeft - altitudeRight
        }
    }
}
