package com.universe.vladiviva5991gmail.moons.mvp.activities.main

import android.content.ComponentName
import android.os.IBinder
import android.util.Log
import com.universe.vladiviva5991gmail.moons.domain.entity.MoonEntity
import com.universe.vladiviva5991gmail.moons.domain.usecase.GetMoon
import com.universe.vladiviva5991gmail.moons.mvp.App
import com.universe.vladiviva5991gmail.moons.mvp.activities.calculations.MoonPhase
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainPresenter : InterMainPresenter(), NetDependent {
    private var netService: NetService? = null
    private val netReceiver: NetReceiver = NetReceiver(this)
    private val gpsReceiver = GpsReceiver()

    override fun createInject() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var getMoon: GetMoon

    override fun onStart() {
        super.onStart()
        view.onBindService(this)
    }

    //TODO Надо добавить диалог, на случай, если пропадёт интернет,
    //TODO чтобы донести до пользователя, что данные могут быть не точны
    override fun onResume() {
        super.onResume()
        view.registrationReceiver(netReceiver)
        view.registrationReceiver(gpsReceiver)
        createInject()
        startDownloading()
        onMainClick()
    }

    override fun onPause() {
        super.onPause()
        view.unregistrationReceiver(netReceiver)
        view.unregistrationReceiver(gpsReceiver)
    }

    override fun onStop() {
        super.onStop()
        view.unBindService(this)
    }

    override fun startDownloading() {
        view.showProgress()
        getMoon.get().subscribe(object : Observer<MoonEntity> {
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(t: MoonEntity) {
                Log.e("State moon right now", t.age.toString())
                Log.e("State moon right now", t.stage)
                Log.e("State moon right now", t.illumination.toString())
                Log.e("State moon right now", t.dfs.toString())
                Log.e("State moon right now", t.dfcoe.toString())
                view.setupAge(MoonPhase.timeConversion(t.age))
                view.setupPhase(t.stage)
            }

            override fun onError(e: Throwable) {
                Log.e("AAAA", "THINK BAD CONNECTION")
                staticColculation()
                view.hideProgress()
            }

            override fun onComplete() {
                view.hideProgress()
            }
        })

    }

    override fun onMainClick() {
        view.onClickInfo()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        view.setBound(false)
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.e("onServiceConnected", "Start Service")
        val binder: NetService.WifiBinder = service as NetService.WifiBinder
        netService = binder.getService()
        view.setBound(true)
        makeRequest()

    }

    override fun makeRequest() {
        if (netService!!.getWifiState()) {
            startDownloading()
            Log.e("makeRequest", "Start download")
        } else {
            staticColculation()
        }
    }

    override fun staticColculation() {
        val calendar = view.getCalendar()
        val total = MoonPhase.getMoonAge(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH))

        view.setupAge(MoonPhase.timeConversion(total))
        if (total < 15.0) {
            view.setupPhase("waxing")
        } else {
            view.setupPhase("waning")
        }
    }

}