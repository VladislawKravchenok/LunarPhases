package com.universe.vladiviva5991gmail.moons.mvp.activities.mooninfo

import android.util.Log
import android.widget.ImageView

class MoonPresenter : InterMoonPresenter() {
    override fun createInject() {}

    override fun onResume() {
        super.onResume()
    }

    override fun loadImage(view: ImageView, gifUrl: String) {
        Log.e("AAA","start load gif")

    }
}