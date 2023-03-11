package com.auterion.tazama.libvehicle

/**
 * Represents a vehicle for a consumer. That is, one will be able to subscribe to streams of
 * data (e.g. telemetry) and send commands (e.g. "take a photo") from that object. Typically that
 * is what the UI side wants.
 */
interface Vehicle {
    val telemetry: Telemetry
    val camera: Camera
}

/**
 * Represents a vehicle from a producer. This mostly provides access to the streams of data in
 * write mode. For instance, emitting a telemetry.position event can be done from here, and will
 * be reflected in the `Vehicle` object for the consumer. Typically that is what the SDK side wants.
 */
interface VehicleWriter {
    val telemetryWriter: TelemetryWriter
    val cameraWriter: CameraWriter

    /**
     * Set all the streams to their default values (e.g. "0" or "N/A"). This is useful e.g. when
     * connecting to a new drone/SDK: the `Vehicle` object stays intact, but its values are all
     * reset.
     */
    fun reset()
}
