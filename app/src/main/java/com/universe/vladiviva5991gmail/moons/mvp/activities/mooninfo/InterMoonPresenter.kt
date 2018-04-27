package com.universe.vladiviva5991gmail.moons.mvp.activities.mooninfo

import android.widget.ImageView
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BasePresenter
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.Router

abstract class InterMoonPresenter : BasePresenter<MoonView, Router>() {
    abstract fun loadImage(view: ImageView, gifUrl: String)
}