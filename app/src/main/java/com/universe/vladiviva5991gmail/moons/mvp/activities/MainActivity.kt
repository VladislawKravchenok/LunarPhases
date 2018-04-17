package com.universe.vladiviva5991gmail.moons.mvp.activities


import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.universe.vladiviva5991gmail.moons.R
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseMainActivity
import com.universe.vladiviva5991gmail.moons.mvp.location.BaseLocation
import com.universe.vladiviva5991gmail.moons.mvp.location.LocationRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseMainActivity<BaseLocation>() {
    override fun provideLocation()
            : BaseLocation = LocationRequest(applicationContext, this@MainActivity)

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
        return super.onOptionsItemSelected(item)
    }


}