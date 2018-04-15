package com.universe.vladiviva5991gmail.moons.mvp

import com.universe.vladiviva5991gmail.moons.mvp.activities.TestActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModul::class))
interface AppComponent {
    fun inject(testActivity: TestActivity)
}