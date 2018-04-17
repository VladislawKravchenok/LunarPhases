package com.universe.vladiviva5991gmail.moons.mvp

import com.universe.vladiviva5991gmail.moons.mvp.activities.SplashPresenter
import com.universe.vladiviva5991gmail.moons.mvp.location.LocationModul
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(LocationModul::class,AppModul::class))
interface AppComponent {
    //fun inject(testActivity: SplashActivity)
    fun inject(splashPresenter: SplashPresenter)
}