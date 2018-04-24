package com.universe.vladiviva5991gmail.moons.mvp.activities.main

import android.content.ServiceConnection
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BasePresenter
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseView
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.Router

abstract class InterMainPresenter : BasePresenter<MainView,Router>(), ServiceConnection {
    abstract fun onMainClick()
}