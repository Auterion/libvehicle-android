/*
* This Source Code Form is subject to the terms of the Mozilla Public
* License, v. 2.0. If a copy of the MPL was not distributed with this
* file, You can obtain one at https://mozilla.org/MPL/2.0/.
*/

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