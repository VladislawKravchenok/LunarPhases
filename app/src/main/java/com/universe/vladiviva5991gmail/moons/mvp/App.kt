package com.universe.vladiviva5991gmail.moons.mvp

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.universe.vladiviva5991gmail.moons.BuildConfig

class App : Application() {
    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        initInject()
        initLeakDetection()
       // Fabric.with(this, Crashlytics())

    }

    private fun initInject() {
        appComponent = DaggerAppComponent
                .builder()
                .appModul(AppModul(this@App))
                .build()
    }

    private fun initLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this@App)
        }
    }


}