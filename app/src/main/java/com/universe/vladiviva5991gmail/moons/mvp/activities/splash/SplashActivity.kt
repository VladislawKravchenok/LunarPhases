package com.universe.vladiviva5991gmail.moons.mvp.activities.splash

import android.os.Bundle
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BasePresenter
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseSplashActivity
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseView
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.Router
import com.universe.vladiviva5991gmail.moons.mvp.activities.main.MainRouter
import javax.inject.Singleton

@Singleton
class SplashActivity: BaseSplashActivity<BasePresenter<BaseView,Router>>() {

    override fun providePresenter(): BasePresenter<BaseView,Router> = SplashPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainRouter(this).navigateToMain()
        finish()
    }

}