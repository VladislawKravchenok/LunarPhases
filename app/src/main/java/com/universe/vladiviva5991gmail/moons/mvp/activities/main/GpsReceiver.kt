package com.universe.vladiviva5991gmail.moons.mvp.activities.main

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent



class GpsReceiver
constructor(private val presenter: NetDependent)
    : BroadcastReceiver()  {
    @SuppressLint("ServiceCast")
    override fun onReceive(context: Context?, intent: Intent?) {

    }
}