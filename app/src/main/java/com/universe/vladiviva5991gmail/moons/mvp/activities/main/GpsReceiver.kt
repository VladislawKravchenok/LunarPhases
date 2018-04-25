package com.universe.vladiviva5991gmail.moons.mvp.activities.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.location.LocationManager
import android.util.Log
import javax.inject.Singleton

@Singleton
class GpsReceiver
    : BroadcastReceiver()  {
    override fun onReceive(context: Context?, intent: Intent?) {

       val locationManager = context?.getSystemService(LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.e("About GPS", "GPS is Enabled in your devide")
        } else {
            Log.e("About GPS", "GPS is dead")
        }
    }
}