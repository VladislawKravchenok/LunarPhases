package com.universe.vladiviva5991gmail.moons.mvp.activities.mooninfo

import android.app.Activity
import android.content.Intent
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.Router
/**
 * пульт перехода на moon activity
 * */
class MoonRouter
constructor(activity: Activity,val image:Int)
    : Router(activity) {

    fun navigateToMoon() {
        val intent = Intent(activity, MoonActivity::class.java)
        intent.putExtra("image",image)
        activity.startActivity(intent)

    }
}