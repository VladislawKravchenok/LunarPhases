package com.universe.vladiviva5991gmail.moons.mvp.activities.base

import android.os.Bundle
import com.universe.vladiviva5991gmail.moons.R
import com.universe.vladiviva5991gmail.moons.mvp.activities.mooninfo.InterMoonPresenter

abstract class BaseMoonActivity<Presenter : InterMoonPresenter>
    :BaseActivity(){

    protected lateinit var presenter: Presenter

    abstract fun providePresenter(): Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moon)
        presenter = providePresenter()
    }

    override fun onResume() {
        super.onResume()

    }
}