package com.universe.vladiviva5991gmail.moons.mvp

import com.universe.vladiviva5991gmail.moons.mvp.activities.SplashPresenter
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseActivityGuide
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModul::class))
interface AppComponent {
    //fun inject(baseActivityGuide: BaseActivityGuide)
    fun inject(splashPresenter: SplashPresenter)
}