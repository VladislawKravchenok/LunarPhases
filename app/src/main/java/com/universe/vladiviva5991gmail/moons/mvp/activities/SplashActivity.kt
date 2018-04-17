package com.universe.vladiviva5991gmail.moons.mvp.activities

import android.content.Intent
import android.os.Bundle
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BasePresenter
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseSplashActivity
import javax.inject.Singleton

@Singleton
class SplashActivity: BaseSplashActivity<BasePresenter>() {

    override fun providePresenter(): BasePresenter = SplashPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}