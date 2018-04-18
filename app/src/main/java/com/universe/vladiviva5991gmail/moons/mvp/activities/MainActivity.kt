package com.universe.vladiviva5991gmail.moons.mvp.activities


import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.universe.vladiviva5991gmail.moons.R
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseMainActivity
import com.universe.vladiviva5991gmail.moons.mvp.location.BaseLocation
import com.universe.vladiviva5991gmail.moons.mvp.location.LocationRequest
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : BaseMainActivity<BaseLocation>() {
    override fun provideLocation()
            : BaseLocation = LocationRequest(applicationContext, this@MainActivity)

    val calendar = Calendar.getInstance()

    val dataSetListener = object: DatePickerDialog.OnDateSetListener{
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "yyyy-MM-dd"
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
                Toast.makeText(this@MainActivity,sdf.format(calendar.time),Toast.LENGTH_LONG).show()
            } else {
                Log.e("SimpleDateFormat","your version android is low")
            }



        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

    }


    /**
     * Вызывается, когда ток Window активности получает или теряет фокус.*/
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            val decorView: View = window.decorView
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
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
        Log.e("click_on_icon_bar","calendar")
        when(item?.itemId){
            R.id.action_calendar -> {
                Toast.makeText(this,"SHOW CALENDAR!",Toast.LENGTH_LONG).show()
                DatePickerDialog(this@MainActivity,
                        dataSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}