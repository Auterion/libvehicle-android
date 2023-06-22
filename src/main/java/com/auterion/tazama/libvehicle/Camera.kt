/*
* This Source Code Form is subject to the terms of the Mozilla Public
* License, v. 2.0. If a copy of the MPL was not distributed with this
* file, You can obtain one at https://mozilla.org/MPL/2.0/.
*/

package com.auterion.tazama.libvehicle

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface Camera {
    val videoStreamInfo: StateFlow<VideoStreamInfo?>
}

interface CameraWriter {
    val videoStreamInfoWriter: MutableStateFlow<VideoStreamInfo?>
}

class CameraImpl : Camera, CameraWriter {
    override val videoStreamInfoWriter = MutableStateFlow<VideoStreamInfo?>(null)
    override val videoStreamInfo = videoStreamInfoWriter.asStateFlow()
}

data class VideoStreamInfo(val uri: String)
