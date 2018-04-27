package com.universe.vladiviva5991gmail.moons.mvp.activities.base

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import com.universe.vladiviva5991gmail.moons.R
import kotlinx.android.synthetic.main.activity_main.*
import tourguide.tourguide.Overlay
import tourguide.tourguide.Pointer
import tourguide.tourguide.ToolTip
import tourguide.tourguide.TourGuide

/**
 * Промежуточное активити отвечающее за отображение подсказок
 *
 * Нужен только при первом запуске приложения
 * */
abstract class BaseActivityGuide : BaseActivity() {

    private lateinit var tourGuide: TourGuide
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = this.getSharedPreferences("firstRun", android.content.Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        firstRunChecker()
    }

    /**
     * Проверяет были ли показаны подсказки
     *
     * @param prefs содержит закэшированную информацию о каком-то событии
     * */
    private fun firstRunChecker(){
        if (prefs.getBoolean("firstrun", true)) {
            prefs.edit().putBoolean("firstrun", false).apply()
            informationSigns(
                    info_table,
                    "Информация о текущем состоянии луны",
                    1)
        }
    }

    private fun informationSigns(view: View, title: String, total: Int) {
        tourGuide = TourGuide.init(this@BaseActivityGuide).with(TourGuide.Technique.CLICK)
                .setPointer(Pointer())
                .setToolTip(ToolTip()
                        .setGravity(Gravity.TOP)
                        .setShadow(true)
                        .setTitle(title))
                .setOverlay(Overlay()
                        .setBackgroundColor(Color.parseColor("#aa979ea4"))
                        .setOnClickListener {
                            startGuide(total)
                        })
                .playOn(view)
    }


    private fun startGuide(total: Int) {
        tourGuide.cleanUp()
        when (total) {
            1 -> {
                informationSigns(
                        moon_wrapper,
                        "Иллюстрация текущего состояния луны",
                        2)
            }
            2 -> {
                informationSigns(
                        next_day,
                        "Позволяет перейти и увидеть состояние луны на завтра",
                        3)
            }
            else -> {
                Log.e("ААААА", "Вводный туториал закончен")
            }
        }
    }
}
