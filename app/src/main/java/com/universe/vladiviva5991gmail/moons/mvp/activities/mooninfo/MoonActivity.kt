package com.universe.vladiviva5991gmail.moons.mvp.activities.mooninfo

import android.os.Bundle
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseMoonActivity
import kotlinx.android.synthetic.main.activity_moon.*

/**
 * Этот экран содержит детальную информацию о луне как о спутнике земли
 * */
class MoonActivity : BaseMoonActivity<InterMoonPresenter>() {


    override fun providePresenter(): InterMoonPresenter = MoonPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        transmitted_view.setImageResource(intent.getIntExtra("image",1))
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(com.universe.vladiviva5991gmail.moons.R.anim.anim_in, com.universe.vladiviva5991gmail.moons.R.anim.anim_out)
    }

}