package com.universe.vladiviva5991gmail.moons.mvp.activities.base

import android.annotation.SuppressLint
import android.content.*
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants.Companion.GREENWICH_DEFOULT_COORDINATES_LATITUDE
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants.Companion.GREENWICH_DEFOULT_COORDINATES_LONGITUDE
import com.universe.vladiviva5991gmail.moons.mvp.activities.appInfo.InfoRouter
import com.universe.vladiviva5991gmail.moons.mvp.activities.main.MainView
import com.universe.vladiviva5991gmail.moons.mvp.activities.main.NetService
import com.universe.vladiviva5991gmail.moons.mvp.location.BaseLocation
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

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

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        request.onStart()
    }


    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
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

    override fun hideProgress() {
        main_bar.visibility = View.INVISIBLE
    }

    override fun setupAge(a: String) {
        age.text = a

    }

    override fun setupPhase(f: String) {
        if (f == "waxing") {
            phase.text = "Растущая луна"
        } else if (f == "waning") {
            phase.text = "Стареющая луна"
        }

    }

    override fun onClickInfo() {
        RxView.clicks(info_view).debounce(1, TimeUnit.SECONDS).subscribe {
            InfoRouter(this).navigationToInfo()
        }
    }

    //*****Service and broadcast**************START
    private var bound: Boolean = false

    override fun setBound(value: Boolean) {
        bound = value
    }

    override fun onBindService(conn: ServiceConnection) {
        val intent = Intent(this, NetService::class.java)
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
        bound = true
    }

    override fun unBindService(conn: ServiceConnection) {
        if (bound) {
            unbindService(conn)
            bound = false
        }
    }

    override fun registrationReceiver(receiver: BroadcastReceiver) {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(receiver, intentFilter)
    }

    override fun unregistrationReceiver(receiver: BroadcastReceiver) {
        unregisterReceiver(receiver)
    }
    //*****Service and broadcast**************END

}