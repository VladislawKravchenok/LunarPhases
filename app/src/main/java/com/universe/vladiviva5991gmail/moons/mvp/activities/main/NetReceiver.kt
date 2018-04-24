package com.universe.vladiviva5991gmail.moons.mvp.activities.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log

class NetReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val connectivityManager
                = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo

        if (netInfo != null && netInfo.type == ConnectivityManager.TYPE_WIFI) {
            //когда включается сделать запрос и подтянуть данные из рест и сделать пометку в sharedpreference
            //если запроса ещё не было,

            Log.e("AAAA","wifi-on")
        } else {
            Log.e("AAAA","wifi-off")
        }
    }
}