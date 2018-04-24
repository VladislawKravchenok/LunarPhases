package com.universe.vladiviva5991gmail.moons.mvp.activities.main


import android.app.DatePickerDialog
import android.content.ComponentName
import android.content.ServiceConnection
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import com.universe.vladiviva5991gmail.moons.R
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseMainActivity
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BasePresenter
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseView
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.Router
import com.universe.vladiviva5991gmail.moons.mvp.location.BaseLocation
import com.universe.vladiviva5991gmail.moons.mvp.location.LocRequest
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity
    : BaseMainActivity<BaseLocation, BasePresenter<MainView, Router>, Router>() {

    override fun provideLocation(): BaseLocation = LocRequest(applicationContext, this@MainActivity)
    override fun providePresenter(): BasePresenter<MainView, Router> = MainPresenter()
    override fun provideRouter(): Router = MainRouter(this@MainActivity)

    private val calendar = Calendar.getInstance()
    override fun getCalendar():Calendar = calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        /* //TODO УБРАТЬ АПАСЛЯ ЭТОТ УЖАС
         val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.anim_button)
         RxView.clicks(next_day)
                 .debounce(200,TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                 .subscribe { next_day.startAnimation(animation) }
         RxView.clicks(previous_day)
                 .debounce(200,TimeUnit.MILLISECONDS,AndroidSchedulers.mainThread())
                 .subscribe { previous_day.startAnimation(animation) }*/
    }


    /**
     *  В этом методе можно загрузить собственный ресурс меню
     *  (определенный в XML) в класс Menu, имеющийся в обратном вызове*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optional_menu, menu)
        return super.onCreateOptionsMenu(menu)

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
                DatePickerDialog(this@MainActivity,
                        dataSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }



    //TODO Сделать покрасивее
    private val dataSetListener = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "yyyy-MM-dd"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)

                Toast.makeText(this@MainActivity, sdf.format(calendar.time), Toast.LENGTH_LONG).show()
            } else {
                Log.e("SimpleDateFormat", "your version android is low")
            }
        }
    }



}