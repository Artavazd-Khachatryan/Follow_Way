package com.useruser.followay.domain.utils

import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class GPSTracker(val context: Context) {

    companion object {

        private val TAG = "GPSTracker"

        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 1F // meters * 10
        private const val MIN_TIME_UPDATES: Long = 5000
    }


    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context.applicationContext)
    private var locationCallback: LocationCallback? = null
    private val location = fusedLocationClient.lastLocation

    private val currentLocation = PublishSubject.create<Location>()
    private val currentLocationUpdates = PublishSubject.create<Location>()


    fun getCurrentLocation(): Observable<Location>? {
        location.addOnSuccessListener { currentLocation.onNext(it) }
        return currentLocation.hide()
    }

    fun requestLocationUpdates(): Observable<Location> {
        val currentLocationRequest = LocationRequest()
        currentLocationRequest.setInterval(MIN_TIME_UPDATES)
            .setFastestInterval(0)
            .setMaxWaitTime(0)
            .setSmallestDisplacement(MIN_DISTANCE_CHANGE_FOR_UPDATES)
            .priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        fusedLocationClient.requestLocationUpdates(currentLocationRequest, object :
            LocationCallback() {

            override fun onLocationResult(locatinResult: LocationResult?) {
                super.onLocationResult(locatinResult)
                locatinResult?.run {
                    currentLocationUpdates.onNext(lastLocation)
                }

            }

        }, Looper.getMainLooper())


        return currentLocationUpdates.hide()
    }

    fun removeLocationUpdates() {
        locationCallback?.let {
            fusedLocationClient.removeLocationUpdates(it)
        }
    }

}