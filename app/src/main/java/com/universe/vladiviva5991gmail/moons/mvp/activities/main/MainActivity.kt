package com.universe.vladiviva5991gmail.moons.mvp.activities.main


import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.DatePicker
import com.jakewharton.rxbinding2.view.RxView
import com.universe.vladiviva5991gmail.moons.R
import com.universe.vladiviva5991gmail.moons.R.id.*
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseMainActivity
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BasePresenter
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.Router
import com.universe.vladiviva5991gmail.moons.mvp.activities.calculations.DateConverter
import com.universe.vladiviva5991gmail.moons.mvp.location.BaseLocation
import com.universe.vladiviva5991gmail.moons.mvp.location.LocRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
class MainActivity
    : BaseMainActivity<BaseLocation, BasePresenter<MainView, Router>, Router>() {

    override fun provideLocation(): BaseLocation = LocRequest(applicationContext, this@MainActivity)
    override fun providePresenter(): BasePresenter<MainView, Router> = MainPresenter()
    override fun provideRouter(): Router = MainRouter(this@MainActivity)

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        initDatePicker()
       // val message : LocationDialogFragment = LocationDialogFragment().show
    }


    /**
     *  В этом методе можно загрузить собственный ресурс меню
     *  (определенный в XML) в класс Menu, имеющийся в обратном вызове*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optional_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    /**
     * Реагирует на выбор даты в календаре(datapickerdialog)
     * */
    private val dateSetListener = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            presenter.actionWhenChooseDate()
            setupDate()
        }
    }

    /**-Обработка нажатий
     * Когда пользователь выбирает пункт меню параметров
     * (в том числе пункты действий из строки действий),
     * система вызывает метод onOptionsItemSelected() вашей операции.
     * Этот метод передает выбранный класс MenuItem.
     * Идентифицировать пункт меню можно, вызвав метод getItemId(),
     * который возвращает уникальный идентификатор пункта меню
     * (определенный атрибутом android:id из ресурса меню )*/
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_calendar -> {
                if (datePickerDialog.isShowing) {
                    Log.e("AAAA", "CALENDAR SHOWING RIGHTNOW")
                } else {
                    datePickerDialog.show()
                    Log.e("AAAA", "CALENDAR SHOW RIGHTNOW")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setupDate() {
        val string: String = DateConverter.engToRus(calendar.time)
        textview_date.text = string
    }

    override fun setupTodayDate() {
        textview_date.text = DateConverter.engToRus(Date())
    }

    override fun getCalendar(): Calendar = calendar

    private fun initDatePicker() {
        datePickerDialog = DatePickerDialog(this@MainActivity,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
    }

    override fun onClickInfo() {
        super.onClickInfo()
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.anim_button)
        RxView.clicks(next_day)
                .debounce(100, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribe {
                    next_day.startAnimation(animation)
                    calendar.add(Calendar.DATE, 1)
                    presenter.actionWhenChooseDate()
                    setupDate()
                }
        RxView.clicks(previous_day)
                .debounce(100, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribe {
                    previous_day.startAnimation(animation)
                    calendar.add(Calendar.DATE, -1)
                    presenter.actionWhenChooseDate()
                    setupDate()
                }
    }
}