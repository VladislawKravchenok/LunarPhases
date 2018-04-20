package com.universe.vladiviva5991gmail.moons.mvp.activities

import com.universe.vladiviva5991gmail.moons.data.sharedPrefs.AppSharedPrefs
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BasePresenter
import javax.inject.Inject

class MainPresenter : BasePresenter() {


    @Inject
    lateinit var appSharedPrefs: AppSharedPrefs

    override fun createInject() {
        //TODO inject this
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}