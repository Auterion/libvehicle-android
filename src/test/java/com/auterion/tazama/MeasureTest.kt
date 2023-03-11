package com.auterion.tazama

import com.auterion.tazama.libvehicle.Measure.MeasurementSystem.IMPERIAL
import com.auterion.tazama.libvehicle.Measure.MeasurementSystem.METRIC
import org.junit.Assert.*
import org.junit.Test

class MeasureTest {
    @Test
    fun speed_defaultsToZero() {
        assertEquals(0.0, com.auterion.tazama.libvehicle.Speed().value, 0.0)
    }

    @Test
    fun speed_isCorrect() {
        assertEquals(24.0, com.auterion.tazama.libvehicle.Speed(24.0).value, 0.0)
    }

    @Test
    fun speed_defaultsToMetric() {
        assertEquals(METRIC, com.auterion.tazama.libvehicle.Speed().measurementSystem)
    }

    @Test
    fun speed_convertsToImperial() {
        val speedMetric = com.auterion.tazama.libvehicle.Speed(42.24)

        val speedImperial = speedMetric.toImperial()

        assertEquals(138.583, speedImperial.value, 0.0001)
    }

    @Test
    fun speed_constructsAsImperial() {
        assertEquals(IMPERIAL, com.auterion.tazama.libvehicle.Speed(measurementSystem = IMPERIAL).measurementSystem)
    }

    @Test
    fun speed_convertsToMetric() {
        val speedImperial = com.auterion.tazama.libvehicle.Speed(120.6, IMPERIAL)

        val speedMetric = speedImperial.toMetric()

        assertEquals(36.759, speedMetric.value, 0.0001)
    }

    @Test
    fun speed_convertsToImperialAndBack() {
        val speedMetric = com.auterion.tazama.libvehicle.Speed(12.3)

        val speedImperial = speedMetric.toImperial()
        val speedMetricBack = speedImperial.toMetric()

        assertEquals(speedMetric, speedMetricBack)
    }

    @Test
    fun speed_unitIsCorrectInMetric() {
        assertEquals("m/s", com.auterion.tazama.libvehicle.Speed().unit)
    }

    @Test
    fun speed_unitIsCorrectInImperial() {
        assertEquals("ft/s", com.auterion.tazama.libvehicle.Speed(measurementSystem = IMPERIAL).unit)
    }

    @Test
    fun speed_equalsOperatorIsCorrect() {
        val speedMetric1 = com.auterion.tazama.libvehicle.Speed(23.2)
        val speedMetric2 = com.auterion.tazama.libvehicle.Speed(23.2)
        val speedMetric3 = com.auterion.tazama.libvehicle.Speed(43.1)
        assertEquals(speedMetric1, speedMetric2)
        assertNotEquals(speedMetric1, speedMetric3)

        val speedImperial1 = com.auterion.tazama.libvehicle.Speed(43.1, IMPERIAL)
        val speedImperial2 = com.auterion.tazama.libvehicle.Speed(43.1, IMPERIAL)
        val speedImperial3 = com.auterion.tazama.libvehicle.Speed(23.2, IMPERIAL)
        assertEquals(speedImperial1, speedImperial2)
        assertNotEquals(speedImperial1, speedImperial3)

        assertNotEquals(speedMetric1, speedImperial1)
        assertNotEquals(speedMetric1, speedImperial3)
    }

    @Test
    fun speed_hashcodeIsCorrect() {
        val speedMetric1 = com.auterion.tazama.libvehicle.Speed(23.2)
        val speedMetric2 = com.auterion.tazama.libvehicle.Speed(23.2)
        val speedMetric3 = com.auterion.tazama.libvehicle.Speed(43.1)
        assertEquals(speedMetric1.hashCode(), speedMetric2.hashCode())
        assertNotEquals(speedMetric1.hashCode(), speedMetric3.hashCode())

        val speedImperial1 = com.auterion.tazama.libvehicle.Speed(43.1, IMPERIAL)
        val speedImperial2 = com.auterion.tazama.libvehicle.Speed(43.1, IMPERIAL)
        val speedImperial3 = com.auterion.tazama.libvehicle.Speed(23.2, IMPERIAL)
        assertEquals(speedImperial1.hashCode(), speedImperial2.hashCode())
        assertNotEquals(speedImperial1.hashCode(), speedImperial3.hashCode())

        assertNotEquals(speedMetric1.hashCode(), speedImperial1.hashCode())
        assertNotEquals(speedMetric1.hashCode(), speedImperial3.hashCode())
    }

    @Test
    fun distance_defaultsToZero() {
        assertEquals(0.0, com.auterion.tazama.libvehicle.Distance().value, 0.0)
    }

    @Test
    fun distance_isCorrect() {
        assertEquals(24.0, com.auterion.tazama.libvehicle.Distance(24.0).value, 0.0)
    }

    @Test
    fun distance_defaultsToMetric() {
        assertEquals(METRIC, com.auterion.tazama.libvehicle.Distance().measurementSystem)
    }

    @Test
    fun distance_convertsToImperial() {
        val distanceMetric = com.auterion.tazama.libvehicle.Distance(42.24)

        val distanceImperial = distanceMetric.toImperial()

        assertEquals(138.583, distanceImperial.value, 0.0001)
    }

    @Test
    fun distance_constructsAsImperial() {
        assertEquals(IMPERIAL, com.auterion.tazama.libvehicle.Distance(measurementSystem = IMPERIAL).measurementSystem)
    }

    @Test
    fun distance_convertsToMetric() {
        val distanceImperial = com.auterion.tazama.libvehicle.Distance(120.6, IMPERIAL)

        val distanceMetric = distanceImperial.toMetric()

        assertEquals(36.759, distanceMetric.value, 0.0001)
    }

    @Test
    fun distance_convertsToImperialAndBack() {
        val distanceMetric = com.auterion.tazama.libvehicle.Distance(12.3)

        val distanceImperial = distanceMetric.toImperial()
        val distanceMetricBack = distanceImperial.toMetric()

        assertEquals(distanceMetric, distanceMetricBack)
    }

    @Test
    fun distance_unitIsCorrectInMetric() {
        assertEquals("m", com.auterion.tazama.libvehicle.Distance().unit)
    }

    @Test
    fun distance_unitIsCorrectInImperial() {
        assertEquals("ft", com.auterion.tazama.libvehicle.Distance(measurementSystem = IMPERIAL).unit)
    }

    @Test
    fun distance_compareToIsCorrect() {
        assertTrue(
            com.auterion.tazama.libvehicle.Distance(22.0) < com.auterion.tazama.libvehicle.Distance(
                24.2
            )
        )
        assertTrue(
            com.auterion.tazama.libvehicle.Distance(12.0) > com.auterion.tazama.libvehicle.Distance(
                3.8
            )
        )
        assertTrue(
            com.auterion.tazama.libvehicle.Distance(13.2) > com.auterion.tazama.libvehicle.Distance(
                -42.8
            )
        )

        assertFalse(
            com.auterion.tazama.libvehicle.Distance(0.0) < com.auterion.tazama.libvehicle.Distance(
                0.0
            )
        )
        assertFalse(
            com.auterion.tazama.libvehicle.Distance(0.0) > com.auterion.tazama.libvehicle.Distance(
                0.0
            )
        )
    }

    @Test
    fun distance_equalsOperatorIsCorrect() {
        val distanceMetric1 = com.auterion.tazama.libvehicle.Distance(23.2)
        val distanceMetric2 = com.auterion.tazama.libvehicle.Distance(23.2)
        val distanceMetric3 = com.auterion.tazama.libvehicle.Distance(43.1)
        assertEquals(distanceMetric1, distanceMetric2)
        assertNotEquals(distanceMetric1, distanceMetric3)

        val distanceImperial1 = com.auterion.tazama.libvehicle.Distance(43.1, IMPERIAL)
        val distanceImperial2 = com.auterion.tazama.libvehicle.Distance(43.1, IMPERIAL)
        val distanceImperial3 = com.auterion.tazama.libvehicle.Distance(23.2, IMPERIAL)
        assertEquals(distanceImperial1, distanceImperial2)
        assertNotEquals(distanceImperial1, distanceImperial3)

        assertNotEquals(distanceMetric1, distanceImperial1)
        assertNotEquals(distanceMetric1, distanceImperial3)
    }

    @Test
    fun distance_hashcodeIsCorrect() {
        val distanceMetric1 = com.auterion.tazama.libvehicle.Distance(23.2)
        val distanceMetric2 = com.auterion.tazama.libvehicle.Distance(23.2)
        val distanceMetric3 = com.auterion.tazama.libvehicle.Distance(43.1)
        assertEquals(distanceMetric1.hashCode(), distanceMetric2.hashCode())
        assertNotEquals(distanceMetric1.hashCode(), distanceMetric3.hashCode())

        val distanceImperial1 = com.auterion.tazama.libvehicle.Distance(43.1, IMPERIAL)
        val distanceImperial2 = com.auterion.tazama.libvehicle.Distance(43.1, IMPERIAL)
        val distanceImperial3 = com.auterion.tazama.libvehicle.Distance(23.2, IMPERIAL)
        assertEquals(distanceImperial1.hashCode(), distanceImperial2.hashCode())
        assertNotEquals(distanceImperial1.hashCode(), distanceImperial3.hashCode())

        assertNotEquals(distanceMetric1.hashCode(), distanceImperial1.hashCode())
        assertNotEquals(distanceMetric1.hashCode(), distanceImperial3.hashCode())
    }

    @Test
    fun altitude_defaultsToZero() {
        assertEquals(0.0, com.auterion.tazama.libvehicle.Altitude().value, 0.0)
    }

    @Test
    fun altitude_isCorrect() {
        assertEquals(24.0, com.auterion.tazama.libvehicle.Altitude(24.0).value, 0.0)
    }

    @Test
    fun altitude_defaultsToMetric() {
        assertEquals(METRIC, com.auterion.tazama.libvehicle.Altitude().measurementSystem)
    }

    @Test
    fun altitude_convertsToImperial() {
        val altitudeMetric = com.auterion.tazama.libvehicle.Altitude(42.24)

        val altitudeImperial = altitudeMetric.toImperial()

        assertEquals(138.583, altitudeImperial.value, 0.0001)
    }

    @Test
    fun altitude_constructsAsImperial() {
        assertEquals(IMPERIAL, com.auterion.tazama.libvehicle.Altitude(measurementSystem = IMPERIAL).measurementSystem)
    }

    @Test
    fun altitude_convertsToMetric() {
        val altitudeImperial = com.auterion.tazama.libvehicle.Altitude(120.6, IMPERIAL)
        val altitudeExpected = com.auterion.tazama.libvehicle.Altitude(36.759, METRIC)

        val altitudeMetric = altitudeImperial.toMetric()

        assertEquals(altitudeExpected, altitudeMetric)
    }

    @Test
    fun altitude_convertsToImperialAndBack() {
        val altitudeMetric = com.auterion.tazama.libvehicle.Altitude(12.3)

        val altitudeImperial = altitudeMetric.toImperial()
        val altitudeMetricBack = altitudeImperial.toMetric()

        assertEquals(altitudeMetric, altitudeMetricBack)
    }

    @Test
    fun altitude_unitIsCorrectInMetric() {
        assertEquals("m", com.auterion.tazama.libvehicle.Altitude().unit)
    }

    @Test
    fun altitude_unitIsCorrectInImperial() {
        assertEquals("ft", com.auterion.tazama.libvehicle.Altitude(measurementSystem = IMPERIAL).unit)
    }

    @Test
    fun altitude_equalsOperatorIsCorrect() {
        val altitudeMetric1 = com.auterion.tazama.libvehicle.Altitude(23.2)
        val altitudeMetric2 = com.auterion.tazama.libvehicle.Altitude(23.2)
        val altitudeMetric3 = com.auterion.tazama.libvehicle.Altitude(43.1)
        assertEquals(altitudeMetric1, altitudeMetric2)
        assertNotEquals(altitudeMetric1, altitudeMetric3)

        val altitudeImperial1 = com.auterion.tazama.libvehicle.Altitude(43.1, IMPERIAL)
        val altitudeImperial2 = com.auterion.tazama.libvehicle.Altitude(43.1, IMPERIAL)
        val altitudeImperial3 = com.auterion.tazama.libvehicle.Altitude(23.2, IMPERIAL)
        assertEquals(altitudeImperial1, altitudeImperial2)
        assertNotEquals(altitudeImperial1, altitudeImperial3)

        assertNotEquals(altitudeMetric1, altitudeImperial1)
        assertNotEquals(altitudeMetric1, altitudeImperial3)
    }

    @Test
    fun altitude_hashcodeIsCorrect() {
        val altitudeMetric1 = com.auterion.tazama.libvehicle.Altitude(23.2)
        val altitudeMetric2 = com.auterion.tazama.libvehicle.Altitude(23.2)
        val altitudeMetric3 = com.auterion.tazama.libvehicle.Altitude(43.1)
        assertEquals(altitudeMetric1.hashCode(), altitudeMetric2.hashCode())
        assertNotEquals(altitudeMetric1.hashCode(), altitudeMetric3.hashCode())

        val altitudeImperial1 = com.auterion.tazama.libvehicle.Altitude(43.1, IMPERIAL)
        val altitudeImperial2 = com.auterion.tazama.libvehicle.Altitude(43.1, IMPERIAL)
        val altitudeImperial3 = com.auterion.tazama.libvehicle.Altitude(23.2, IMPERIAL)
        assertEquals(altitudeImperial1.hashCode(), altitudeImperial2.hashCode())
        assertNotEquals(altitudeImperial1.hashCode(), altitudeImperial3.hashCode())

        assertNotEquals(altitudeMetric1.hashCode(), altitudeImperial1.hashCode())
        assertNotEquals(altitudeMetric1.hashCode(), altitudeImperial3.hashCode())
    }

    @Test
    fun altitude_minusOperatorIsCorrect() {
        val altitudeLeft = com.auterion.tazama.libvehicle.Altitude(40.2)
        val altitudeRight = com.auterion.tazama.libvehicle.Altitude(18.4)
        val altitudeExpected = com.auterion.tazama.libvehicle.Altitude(21.8)

        val altitudeDiff = altitudeLeft - altitudeRight

        assertEquals(altitudeExpected, altitudeDiff)
    }

    @Test
    fun altitude_minusThrowsIfMeasurementSystemDiffers() {
        val altitudeLeft = com.auterion.tazama.libvehicle.Altitude(40.2)
        val altitudeRight = com.auterion.tazama.libvehicle.Altitude(18.4, IMPERIAL)

        assertThrows(RuntimeException::class.java) {
            altitudeLeft - altitudeRight
        }
    }
}
