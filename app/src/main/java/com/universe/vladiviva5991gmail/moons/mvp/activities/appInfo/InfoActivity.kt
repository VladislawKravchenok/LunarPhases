package com.universe.vladiviva5991gmail.moons.mvp.activities.appInfo

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.universe.vladiviva5991gmail.moons.R
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseActivity
import javax.inject.Singleton

@Singleton
class InfoActivity : BaseActivity() {

    private lateinit var fusedLocation: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        fusedLocation = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onResume() {
        super.onResume()
        getLastLoc()
    }

    @SuppressLint("MissingPermission")
    private fun getLastLoc(){//вызывать из rxPermission в блоке if else = granted
        if(ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            fusedLocation.lastLocation.addOnCompleteListener(this, OnCompleteListener<Location>(){
                Log.e("AAAAA","Широта" + it.getResult().latitude.toString())
                Log.e("AAAAA","Долгота" + it.getResult().longitude.toString())
            } )
        }

    }
}