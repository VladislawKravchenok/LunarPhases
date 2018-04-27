package com.universe.vladiviva5991gmail.moons.mvp.activities.main

import android.app.Activity
import android.content.Intent
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.Router

/**
 * Пульт перехода на main activity
 * */
class MainRouter
constructor(activity: Activity)
    : Router(activity) {

    fun navigateToMain() {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }
}