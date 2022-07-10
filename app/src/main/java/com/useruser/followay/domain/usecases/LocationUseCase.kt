package com.useruser.followay.domain.usecases

import com.useruser.followay.domain.utils.GPSTracker

class LocationUseCase(private val gpsTracker: GPSTracker) {

    fun getCurrentLocation() = gpsTracker.getCurrentLocation()

    fun requestCurrentLocationUpdates() = gpsTracker.requestLocationUpdates()

    fun stopLocationUpdate() = gpsTracker.removeLocationUpdates()
}