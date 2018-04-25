package com.universe.vladiviva5991gmail.moons.mvp.activities.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log

class NetReceiver
constructor(private val presenter: NetDependent)
    : BroadcastReceiver() {

    private var isFirst: Boolean = true

    override fun onReceive(context: Context?, intent: Intent?) {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo

        if (netInfo != null && netInfo.type == ConnectivityManager.TYPE_WIFI) {
            Log.e("AAAA", "wifi-on")
            if (isFirst) {
                isFirst = false
            } else {
                presenter.makeRequest()
            }
        } else {
            presenter.staticColculation()
            Log.e("AAAA", "wifi-off")
        }
    }
}