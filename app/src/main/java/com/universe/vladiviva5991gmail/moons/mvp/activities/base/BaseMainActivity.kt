package com.universe.vladiviva5991gmail.moons.mvp.activities.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.universe.vladiviva5991gmail.moons.R
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants
import com.universe.vladiviva5991gmail.moons.mvp.location.BaseLocation
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseMainActivity<out Location : BaseLocation> : BaseActivity() {

    private lateinit var request: Location

    abstract fun provideLocation(): Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        request = provideLocation()


    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        request.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        request.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     *  Для обработки случая, когда пользователь предоставляет разрешение
     * */
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray) {
        if (requestCode == AppConstants.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> {
                    Log.e("PermissionsResult", "PERMISSION_GRANTED")
                    request.startLocationUpdates()
                }
                PackageManager.PERMISSION_DENIED -> {
                    Log.e("PermissionsResult", "PERMISSION_DENIED")
                    Toast.makeText(this@BaseMainActivity, "For correct information, we need to know your current location", Toast.LENGTH_LONG).show()
                    latitude.text = AppConstants.GREENWICH_DEFOULT_COORDINATES_LATITUDE.toString()
                    longitude.text = AppConstants.GREENWICH_DEFOULT_COORDINATES_LONGITUDE.toString()
                }
            }
        }
    }




}