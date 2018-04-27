package com.universe.vladiviva5991gmail.moons.mvp.activities.appInfo

import android.app.Activity
import android.content.Intent
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.Router
/**
 * пульт перехода на info activity
 * */
class InfoRouter
constructor(activity: Activity) : Router(activity) {

    fun navigateToInfo() {
        val intent = Intent(activity, InfoActivity::class.java)
        activity.startActivity(intent)
    }
}