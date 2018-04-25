package com.universe.vladiviva5991gmail.moons.mvp.activities.main

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.wifi.WifiManager
import android.os.Binder
import android.os.IBinder
import android.util.Log

internal class NetService : Service() {

    private val binder: IBinder = WifiBinder()
    private lateinit var wifiManger: WifiManager
    override fun onBind(intent: Intent?): IBinder = binder

    override fun unbindService(conn: ServiceConnection?) = super.unbindService(conn)


    override fun onCreate() {
        super.onCreate()
        wifiManger = this.getSystemService(Context.WIFI_SERVICE) as WifiManager

    }


    inner class WifiBinder : Binder() {
        fun getService(): NetService = this@NetService
    }

    //true если включён и false если выключен
    fun getWifiState(): Boolean {
        Log.e("AAAA", "wifi-stater")

        return wifiManger.isWifiEnabled
    }

    /*private var wifiState: Boolean = false
    @SuppressLint("MissingPermission")
       fun wifiSelector() {
           Log.e("WifiService", "Selector in Action")
           wifiState = wifiManger.isWifiEnabled
           if (wifiState) {
               wifiManger.isWifiEnabled = false
               wifiState = false
           } else {
               wifiManger.isWifiEnabled = true
               wifiState = true
           }
       }*/
}