package com.universe.vladiviva5991gmail.moons.mvp.activities.main

import android.content.BroadcastReceiver
import android.content.ServiceConnection
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseView
import java.util.*

interface MainView : BaseView {

    fun setupAge(a: String)
    fun setupPhase(f: String)
    fun setupImage(i: Double)
    fun onClickInfo()
    fun setBound(value: Boolean)
    fun getCalendar(): Calendar

    /**Service command's*/
    fun onBindService(conn: ServiceConnection)

    fun unBindService(conn: ServiceConnection)
    fun registrationReceiver(receiver: BroadcastReceiver)
    fun unregistrationReceiver(receiver: BroadcastReceiver)


}