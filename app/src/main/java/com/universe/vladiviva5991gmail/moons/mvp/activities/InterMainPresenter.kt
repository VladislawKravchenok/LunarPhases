package com.universe.vladiviva5991gmail.moons.mvp.activities

import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BasePresenter
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.Router

abstract class InterMainPresenter : BasePresenter<Router>() {
    abstract fun onMainClick()
}