package com.useruser.followay.ui.application

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import android.app.Activity
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject
import com.useruser.followay.di.DaggerAppComponent
import com.useruser.followay.data.locationServise.SendLocationWorker


class BaseApplication : DaggerApplication() {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        SendLocationWorker.startSendLocationService()
    }


    override fun applicationInjector(): AndroidInjector<out BaseApplication> {
        return DaggerAppComponent
            .builder()
            .context(this)
            .build()
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }
}