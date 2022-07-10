package com.useruser.followay.di

import android.content.Context
import com.useruser.followay.data.repository.Repository
import com.useruser.followay.domain.utils.GPSTracker
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule{

    @Singleton
    @Provides
    @JvmStatic
    fun provideGpsTracker(context: Context): GPSTracker {
        return GPSTracker(context)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideRepository(gpsTracker: GPSTracker): Repository {
        return Repository()
    }
}