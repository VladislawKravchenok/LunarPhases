package com.universe.vladiviva5991gmail.moons.mvp.activities.appInfo

import android.os.Bundle
import com.google.android.gms.location.FusedLocationProviderClient
import com.universe.vladiviva5991gmail.moons.R
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BaseActivity
import javax.inject.Singleton

@Singleton
class InfoActivity : BaseActivity() {

    private lateinit var fusedLocation: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(com.universe.vladiviva5991gmail.moons.R.anim.anim_in, com.universe.vladiviva5991gmail.moons.R.anim.anim_out)
    }


}