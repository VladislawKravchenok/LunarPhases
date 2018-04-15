package com.universe.vladiviva5991gmail.moons.mvp.activities

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.universe.vladiviva5991gmail.moons.R
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants.Companion.GREENWICH_DEFOULT_COORDINATES_LATITUDE
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants.Companion.GREENWICH_DEFOULT_COORDINATES_LONGITUDE
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants.Companion.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
import com.universe.vladiviva5991gmail.moons.mvp.location.RequestLocation
import kotlinx.android.synthetic.main.test_activity.*

class TestActivity : AppCompatActivity() {

    private lateinit var request: RequestLocation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity)
        request = RequestLocation(applicationContext, this, latitude_textview, longitude_textview)
    }

    override fun onStart() {
        super.onStart()
        request.onStart()
    }

    override fun onStop() {
        super.onStop()
        request.onStop()
    }

    /**
     * To handle the case where the user grants the permission
     * */
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray) {
        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> {
                    Log.e("PermissionsResult", "PERMISSION_GRANTED")
                    request.startLocationUpdates()
                }
                PackageManager.PERMISSION_DENIED -> {
                    Log.e("PermissionsResult", "PERMISSION_DENIED")
                    Toast.makeText(this@TestActivity, "For correct information, we need to know your current location", Toast.LENGTH_LONG).show()
                    latitude_textview.text = GREENWICH_DEFOULT_COORDINATES_LATITUDE.toString()
                    longitude_textview.text = GREENWICH_DEFOULT_COORDINATES_LONGITUDE.toString()
                }
            }
        }
    }
}