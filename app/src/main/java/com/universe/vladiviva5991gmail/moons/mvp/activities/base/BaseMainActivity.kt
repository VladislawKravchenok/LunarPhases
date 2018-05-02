package com.universe.vladiviva5991gmail.moons.mvp.activities.base

import android.annotation.SuppressLint
import android.app.DatePickerDialog
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
import com.universe.vladiviva5991gmail.moons.mvp.activities.mooninfo.MoonRouter
import com.universe.vladiviva5991gmail.moons.mvp.location.BaseLocation
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

abstract
class BaseMainActivity
<out Location : BaseLocation, Presenter : BasePresenter<MainView, Router>, out R : Router>
    : BaseActivityGuide(), MainView {

    private var request: Location? = null
    private lateinit var router: R
    protected lateinit var presenter: Presenter
    protected lateinit var datePickerDialog: DatePickerDialog

    abstract fun provideLocation(): Location
    abstract fun providePresenter(): Presenter
    abstract fun provideRouter(): R

    @SuppressLint("MissingPermission", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.LOLLIPOP) {
            setLocForLowVersion()
        } else {
            request = provideLocation()
        }

        presenter = providePresenter()
        router = provideRouter()
        presenter.attached(this, router)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()

        request?.onStart()
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
        request?.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettached()
    }

    /**
     * Вызывается, когда нужно запустить работу службы
     *
     * @param conn нужен для мониторинга состояния службы приложения
     * */
    override fun onBindService(conn: ServiceConnection) {
        val intent = Intent(this, NetService::class.java)
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
        bound = true
    }

    /**
     * Вызывается, когда нужно прекратить работу службы
     *
     * @param conn нужен для мониторинга состояния службы приложения
     * */
    override fun unBindService(conn: ServiceConnection) {
        if (bound) {
            unbindService(conn)
            bound = false
        }
    }

    /**
     * Вызывается, когда нужно зарегистрировать приёмник широковещатель(начать прослушивание)
     *
     * @param receiver нужен для динамической регистрации экземпляра этого класса
     * */
    override fun registrationReceiver(receiver: BroadcastReceiver) {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(receiver, intentFilter)
    }

    /**
     * Вызывается, когда нужно зарегистрировать приёмник широковещатель(начать прослушивание)
     *
     * @param receiver нужен для удаления зарегистрированного приёмника широковещателя
     * */
    override fun unregistrationReceiver(receiver: BroadcastReceiver) {
        unregisterReceiver(receiver)
    }

    /**
     * Устанавливает возраст луны на нашем экране
     *
     * @param a это простая строка, содержащая возраст луны в виде '20дней10ч12минут"
     * */
    override fun setupAge(a: String) {
        age.text = a
    }


    /**
     * Устанавливает иллюстрацию(фазу) нашей луны в соответствии с возрастом
     *
     * @param i содержит неконвертированный возраст луны в виде числа с плавающей точкой
     * */
    override fun setupImage(i: Double) {
        moon_phase_representation.setImageResource(getMoonIllustration(i))
    }

    /**
     * Устанавливает фазу луны на нашем экране
     *
     * @param f содержит строку в которой описана фаза луны
     * */
    override fun setupPhase(f: String) {
        if (f == "waxing") {
            phase.text = "Растущая луна"
        } else if (f == "waning") {
            phase.text = "Стареющая луна"
        }
    }

    /**
     * Выбирает иллюстрацию фазы луны в зависимости от её возраста
     *
     * @return возвращает иллюстрацию которую необходимо установить на экран
     *
     * @param i содержит неконвертированный возраст луны в виде числа с плавающей точкой
     * */
    private fun getMoonIllustration(i: Double): Int {
        when (i) {
            in 0.0..1.4 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_one
            in 1.5..2.6 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_two
            in 2.7..3.8 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_three
            in 3.9..5.0 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_four
            in 5.1..6.2 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_five
            in 6.3..7.4 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_six
            in 7.5..8.6 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_seven
            in 8.7..9.7 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_eight
            in 9.8..10.8 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_nine
            in 10.9..11.9 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_ten
            in 12.0..13.0 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_eleven
            in 13.1..14.2 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_twelve
            in 14.3..15.4 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_thirty
            in 15.5..16.7 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_fourteen
            in 16.8..18.0 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_fifteen
            in 18.1..19.4 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_sixteen
            in 19.5..20.6 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_seventeen
            in 20.7..21.8 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_eighteen
            in 21.9..23.0 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_nineteen
            in 23.1..24.9 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_twenty
            in 25.0..26.7 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_twentyone
            in 26.8..28.3 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_twentytwo
            in 28.4..30.0 -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_twentythree
            else -> return com.universe.vladiviva5991gmail.moons.R.drawable.phase_twentythree
        }
    }

    /**
     * Устанавливает координаты 0-го мередиана по умолчанию в том случае, если пользователь
     *      не хочет предоставлять разрешение на обнаружение его местоположения
     **/
    @SuppressLint("SetTextI18n")
    fun applyDefaultCoordinates() {
        latitude_longtude.setTextColor(Color.GREEN)
        latitude_longtude.text =
                GREENWICH_DEFOULT_COORDINATES_LATITUDE + "°с.ш " +
                GREENWICH_DEFOULT_COORDINATES_LONGITUDE + "°в.д"
    }

    /**
     * Показывает progress bar, пока подгружаются данные из api
     * */
    override fun showProgress() {
        main_bar.visibility = View.VISIBLE
    }

    /**
     * Прячет индикатор когда загрузка завершена
     * */
    override fun hideProgress() {
        main_bar.visibility = View.INVISIBLE
    }

    /**
     * Вызывается в самом начале, чтобы назначить действие перехода на другую активити
     * */
    override fun onClickInfo() {
        RxView.clicks(info_view).debounce(50, TimeUnit.MILLISECONDS).subscribe {
            InfoRouter(this).navigateToInfo()
            overridePendingTransition(com.universe.vladiviva5991gmail.moons.R.anim.anim_in, com.universe.vladiviva5991gmail.moons.R.anim.anim_out)
        }
        RxView.clicks(moon_phase_representation).debounce(50, TimeUnit.MILLISECONDS).subscribe {

            MoonRouter(this, com.universe.vladiviva5991gmail.moons.R.drawable.phase_thirty).navigateToMoon()
            overridePendingTransition(com.universe.vladiviva5991gmail.moons.R.anim.anim_in, com.universe.vladiviva5991gmail.moons.R.anim.anim_out)
        }
    }

    private var bound: Boolean = false

    override fun setBound(value: Boolean) {
        bound = value
    }

    @SuppressLint("SetTextI18n")
    private fun setLocForLowVersion() {
        latitude_longtude.setTextColor(Color.GREEN)
        latitude_longtude.text = GREENWICH_DEFOULT_COORDINATES_LATITUDE +
                "°с.ш " + GREENWICH_DEFOULT_COORDINATES_LONGITUDE + "°в.д"
    }
}