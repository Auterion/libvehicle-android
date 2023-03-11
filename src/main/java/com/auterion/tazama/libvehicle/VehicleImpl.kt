package com.auterion.tazama.libvehicle

class VehicleImpl : Vehicle, VehicleWriter {
    override val telemetry: Telemetry = TelemetryImpl()
    override val telemetryWriter = telemetry as TelemetryWriter

    override val camera: Camera = CameraImpl()
    override val cameraWriter = camera as CameraWriter

    override fun reset() {
        telemetryWriter.positionWriter.value = PositionAbsolute()
        cameraWriter.videoStreamInfoWriter.value = VideoStreamInfo("")
    }
}