package com.useruser.followay.data.locationServise

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.work.*
import com.useruser.followay.domain.models.SEND_LOCATION_WORKER
import com.useruser.followay.domain.utils.GPSTracker
import com.useruser.followay.domain.utils.isNetworkConnected
import com.useruser.followay.domain.utils.permissionChecked
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class SendLocationWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    companion object {
        private val TAG = "SendLocationWorker"

        fun startSendLocationService() {
            val work = PeriodicWorkRequestBuilder<SendLocationWorker>(15, TimeUnit.MINUTES)
                .addTag(TAG)
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .build()

            WorkManager.getInstance().enqueueUniquePeriodicWork(
                SEND_LOCATION_WORKER,
                ExistingPeriodicWorkPolicy.KEEP,
                work
            )
        }
    }

    private val gpsTracker = GPSTracker(applicationContext)

    @SuppressLint("CheckResult")
    override fun doWork(): Result {
        Log.d(
            TAG,
            "---------------------------------doWork-----------------${
                SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().time)
            }-------------"
        )


        if (!applicationContext.permissionChecked(ACCESS_FINE_LOCATION)) {

        } else {

            gpsTracker.getCurrentLocation()?.subscribe {
                it?.let {
                    Log.d(
                        TAG,
                        "---------------------------------Location-----------${it.latitude}}----${it.longitude}---------"
                    )
                    sendLocation(it)
                }
            }

        }
        return Result.success()
    }


    fun sendLocation(location: Location) {
        Log.d(
            TAG,
            "---------------------------------sendLocation-----------${location.latitude}}----${location.longitude}---------"
        )

        if (isNetworkConnected(applicationContext)) {
            //send backand
        } else {
            //save data in storage
        }
    }

}