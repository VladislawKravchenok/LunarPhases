package com.universe.vladiviva5991gmail.moons.mvp.location

import android.content.Context
import com.universe.vladiviva5991gmail.moons.mvp.activities.MainActivity
import com.universe.vladiviva5991gmail.moons.mvp.activities.SplashActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModul constructor(private val context: Context) {


    @Provides
    @Singleton
    fun getRequestLocation(context: Context, activity: MainActivity)
            : BaseLocation = LocationRequest(context, activity)

    @Provides
    @Singleton
    fun getActivity(): SplashActivity = SplashActivity()

}