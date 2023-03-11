package com.auterion.tazama.libvehicle.service

interface VehicleService {
    fun connect()
    suspend fun destroy()
}