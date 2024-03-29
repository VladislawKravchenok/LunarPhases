package com.universe.vladiviva5991gmail.moons.mvp

import com.universe.vladiviva5991gmail.moons.mvp.activities.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModul::class))
interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
}