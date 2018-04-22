package com.universe.vladiviva5991gmail.moons.mvp.activities.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants
import com.universe.vladiviva5991gmail.moons.mvp.location.BaseLocation
import kotlinx.android.synthetic.main.activity_main.*

abstract
class BaseMainActivity
<out Location : BaseLocation, out Presenter : BasePresenter<Router>, out R : Router>
    : BaseActivityGuide() {

    private lateinit var request: Location
    private lateinit var presenter: Presenter
    private lateinit var router: R


    abstract fun provideLocation(): Location
    abstract fun providePresenter(): Presenter
    abstract fun provideRouter(): R

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        request = provideLocation()
        presenter = providePresenter()
        router = provideRouter()
        presenter.attach(router)
    }

    override fun onResume() {
        super.onResume()
        request.onStart()
    }

    override fun onStop() {
        super.onStop()
        request.onStop()
    }

    @SuppressLint("SetTextI18n")
    fun applyDefaultCoordinates() {
        latitude_longtude.setTextColor(Color.GREEN)
        latitude_longtude.text = android.location.Location.convert(AppConstants.GREENWICH_DEFOULT_COORDINATES_LATITUDE, android.location.Location.FORMAT_SECONDS) + "°с.ш " +
                android.location.Location.convert(AppConstants.GREENWICH_DEFOULT_COORDINATES_LONGITUDE, android.location.Location.FORMAT_SECONDS) + "°в.д"
    }
}