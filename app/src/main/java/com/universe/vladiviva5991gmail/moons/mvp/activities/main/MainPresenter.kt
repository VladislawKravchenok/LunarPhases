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
    private var checkOnceLoad: Boolean = true

    override fun createInject() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var getMoon: GetMoon

    override fun onStart() {
        super.onStart()
        view.setupDate()
        view.onBindService(this)
    }

    //TODO Надо добавить диалог, на случай, если пропадёт интернет,
    //TODO чтобы донести до пользователя, что данные могут быть не точны
    override fun onResume() {
        super.onResume()
        view.registrationReceiver(netReceiver)
        view.registrationReceiver(gpsReceiver)
        createInject()
        if (checkOnceLoad) {
            startDownloading()
            checkOnceLoad = false
        }
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
                view.setupImage(t.age)
                view.setupTodayDate()
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

    /**
     * Делаем запрос на получение данных из api, если включен интернет,
     *      если нет производим статический расчет
     * */
    override fun makeRequest() {
        if (netService!!.getWifiState()) {
            startDownloading()
            Log.e("makeRequest", "Start download")
        } else {
            staticColculation()
        }
    }

    /**
     * Вызывается если нету связи с интернетом, чтобы произвести статический расчет статуса луны
     *      используя специальные алгоритмы
     *
     * @param total получает из метода determinePhase() возраст луны в виде числа с плавающей точкой
     * */
    override fun staticColculation() {
        val total = determinePhase()
        view.setupAge(MoonPhase.timeConversion(total))
        view.setupImage(total)
        phaseTransfer(total)
    }

    /**
     * Вызывает из activity когда пользователь выбирает дату в календаре
     *
     * @param total получает из метода determinePhase() возраст луны в виде числа с плавающей точкой
     * */
    override fun actionWhenChooseDate() {
        val total = determinePhase()
        view.setupAge(MoonPhase.timeConversion(total))
        view.setupImage(total)
        phaseTransfer(total)
    }

    /**
     * Помогает вычислить возраст луны
     *
     * @return расчет возраста луны на основе алгоритмов описанны в классе MoonPhase
     * */
    private fun determinePhase(): Double {
        val calendar = view.getCalendar()
        return MoonPhase.getMoonAge(MoonPhase.getLunarNumber(
                calendar.get(Calendar.YEAR)),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH))
    }

    /**
     * Устанавливает фазу в зависимости от возраста луны
     *
     * @param total содержит возраст луны в виде числа с плавающей точкой
     * */
    private fun phaseTransfer(total: Double) {
        if (total < 15.0) {
            view.setupPhase("waxing")
        } else {
            view.setupPhase("waning")
        }
    }

    override fun onMainClick() {
        view.onClickInfo()

    }
}