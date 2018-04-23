package com.universe.vladiviva5991gmail.moons.mvp.activities.splash

import android.content.Intent
import android.os.Bundle
import com.universe.vladiviva5991gmail.moons.mvp.activities.main.MainActivity
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BasePresenter
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseSplashActivity
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseView
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.Router
import javax.inject.Singleton

@Singleton
class SplashActivity: BaseSplashActivity<BasePresenter<BaseView,Router>>() {

    override fun providePresenter(): BasePresenter<BaseView,Router> = SplashPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.putExtra("4321","Мама ама криминал")
        startActivity(intent)
        finish()
    }

}