package com.universe.vladiviva5991gmail.moons.mvp.activities

import android.app.Activity
import android.content.Intent
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.Router

class MainRouter
constructor(activity: Activity): Router(activity) {

    fun navigateToMain(id:String){
        val intent = Intent(activity,MainActivity::class.java)
        intent.putExtra("key",id)
        activity.startActivity(intent)
    }
}