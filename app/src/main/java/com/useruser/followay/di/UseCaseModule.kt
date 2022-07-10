package com.useruser.followay.di

import com.useruser.followay.domain.usecases.LocationUseCase
import com.useruser.followay.domain.utils.GPSTracker
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object UseCaseModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideLocationUseCase(gpsTracker: GPSTracker): LocationUseCase {
        return LocationUseCase(gpsTracker)
    }
}