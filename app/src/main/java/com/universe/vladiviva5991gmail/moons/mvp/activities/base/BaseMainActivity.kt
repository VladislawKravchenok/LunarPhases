package com.universe.vladiviva5991gmail.moons.mvp.activities.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants.Companion.GREENWICH_DEFOULT_COORDINATES_LATITUDE
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants.Companion.GREENWICH_DEFOULT_COORDINATES_LONGITUDE
import com.universe.vladiviva5991gmail.moons.mvp.activities.appInfo.InfoRouter
import com.universe.vladiviva5991gmail.moons.mvp.activities.main.MainView
import com.universe.vladiviva5991gmail.moons.mvp.location.BaseLocation
import kotlinx.android.synthetic.main.activity_main.*

abstract
class BaseMainActivity
<out Location : BaseLocation, out Presenter : BasePresenter<MainView, Router>, out R : Router>
    : BaseActivityGuide(), MainView {

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
        presenter.attached(this, router)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        request.onStart()
    }

    override fun onStop() {
        super.onStop()
        request.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettached()
    }

    @SuppressLint("SetTextI18n")
    fun applyDefaultCoordinates() {
        latitude_longtude.setTextColor(Color.GREEN)
        latitude_longtude.text =
                GREENWICH_DEFOULT_COORDINATES_LATITUDE + "°с.ш " +
                GREENWICH_DEFOULT_COORDINATES_LONGITUDE + "°в.д"
    }

    override fun showProgress() {
        main_bar.visibility = View.VISIBLE
    }

    override fun dismissPorgress() {
        main_bar.visibility = View.INVISIBLE
    }

    override fun setupAge(a: String) {
        age.text = a
    }

    override fun setupPhase(f: String) {
        if (f == "waxing") {
            phase.text = "Ростущая"
        } else if (f == "waning") {
            phase.text = "Стареющая"
        }

    }

    override fun onClickInfo() {
        RxView.clicks(info_view).subscribe{
            InfoRouter(this).navigationToInfo()
        }
    }
}