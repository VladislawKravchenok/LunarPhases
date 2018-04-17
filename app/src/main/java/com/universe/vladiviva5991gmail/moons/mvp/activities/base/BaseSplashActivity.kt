package com.universe.vladiviva5991gmail.moons.mvp.activities.base

import android.os.Bundle

abstract class BaseSplashActivity<out Presenter : BasePresenter>
    : BaseActivity() {

    private lateinit var presenter: Presenter

    abstract fun providePresenter(): Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = providePresenter()
    }
}