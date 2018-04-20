package com.universe.vladiviva5991gmail.moons.mvp.location

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants
import com.universe.vladiviva5991gmail.moons.mvp.activities.MainActivity
import com.universe.vladiviva5991gmail.moons.mvp.location.LocationVariables.latitude
import com.universe.vladiviva5991gmail.moons.mvp.location.LocationVariables.longtude
import javax.inject.Singleton

/**
 *  1.Доступ к API Google:
 *  Чтобы установить соединение с API местоположения,
 *  которое предоставляется в библиотеке сервисов Google Play,
 *  нам нужно создать экземпляр GoogleApiCLient .
 *  Клиент API Google предоставляет общую точку входа во все службы Google Play и
 *  управляет сетевым соединением между устройством пользователя и каждым сервисом Google.
 *
 *  2.Управление подключением:
 *  Чтобы начать автоматически управляемое соединение,
 *  мы должны указать реализацию интерфейса OnConnectionFailedListener для получения ошибок соединения,
 *  а также мы можем указать необязательную реализацию интерфейса ConnectionCallbacks,
 *  чтобы узнать, когда автоматически управляемое соединение установлено или приостановлено.
 * */
@Singleton
class LocationRequest
constructor(
        private val context: Context,
        private val activity: MainActivity
) : GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener,
        BaseLocation() {


    private lateinit var location: Location
    private lateinit var locationRequest: LocationRequest
    private val googleApiClient: GoogleApiClient
    private var locationManager: LocationManager

    private val UPDATE_INTERVAL = (30 * 1000).toLong()  /* 30 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */
    //private val NUM_UPDATE: Int = 1 /* just once */

    init {
        requestLocation()
        googleApiClient = GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()

        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        checkLocation() //проверяет включена ли служба определения местоположения в телефоне
    }

    override fun onStart() {

        googleApiClient.connect()
    }

    override fun onStop() {
        googleApiClient.disconnect()
    }

    override fun onConnected(p0: Bundle?) {
        if (checkPermission()) {
            return
        }
        startLocationUpdates()

        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)

        startLocationUpdates()

        latitude = location.latitude.toFloat()
        longtude = location.longitude.toFloat()

    }

    override fun onLocationChanged(location: Location) {
        val msg = "Updated Location: " +
                (location.latitude).toString() + "," +
                (location.longitude).toString()
        latitude = location.latitude.toFloat()
        longtude = location.longitude.toFloat()
        //Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()

    }

    //@SuppressLint("MissingPermission")
    override fun startLocationUpdates() {
        // Create the location request
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                //.setNumUpdates(NUM_UPDATE)
                .setFastestInterval(FASTEST_INTERVAL)

        // Запрос обновления местоположения
        if (checkPermission()) {
            return
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,
                locationRequest, this)
    }


    private fun requestLocation() {
        if (checkPermission()) {
            ActivityCompat.requestPermissions(activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    AppConstants.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
            Log.e("ACCESS_FINE_LOCATION", "not granted")
        } else {
            Log.e("ACCESS_FINE_LOCATION", "granted!")
        }
    }

    private fun checkPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }


    private fun checkLocation(): Boolean {
        if (!isLocationEnabled())
            showAlert()
        return isLocationEnabled()
    }

    private fun isLocationEnabled(): Boolean {
        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onConnectionSuspended(i: Int) {
        Log.e(TAG, "Connection Suspended")
        googleApiClient.connect()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.e(TAG, "Connection failed. Error: " + connectionResult.getErrorCode())
    }

    private fun showAlert() {
       /* Log.e(TAG, "showAlert()")
        val dialog = AlertDialog.Builder(activity)
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " + "use this app")
                .setPositiveButton("Location Settings") { paramDialogInterface, paramInt ->
                    val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(myIntent)
                }
                .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> }
        dialog.show()*/
    }
}