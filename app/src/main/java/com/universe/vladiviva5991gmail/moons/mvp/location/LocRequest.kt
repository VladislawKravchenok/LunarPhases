package com.universe.vladiviva5991gmail.moons.mvp.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.tbruyelle.rxpermissions2.RxPermissions
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants.Companion.FASTEST_INTERVAL
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants.Companion.UPDATE_INTERVAL
import com.universe.vladiviva5991gmail.moons.mvp.activities.main.MainActivity
import com.universe.vladiviva5991gmail.moons.mvp.location.LocationVariables.latitude
import com.universe.vladiviva5991gmail.moons.mvp.location.LocationVariables.longtude
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
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
class LocRequest
constructor(
        private val context: Context,
        private val activity: MainActivity
) : GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener,
        BaseLocation() {

    private lateinit var location: Location
    private lateinit var locationRequest: LocationRequest
    private var locationManager: LocationManager
    private var googleApiClient: GoogleApiClient? = null

    init {
        requestLocation()
        googleApiClient = GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this@LocRequest)
                .addOnConnectionFailedListener(this@LocRequest)
                .addApi(LocationServices.API)
                .build()
        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        checkLocation() //проверяет включена ли служба определения местоположения в телефоне
        googleApiClient?.connect()//подключается к api клиенту
    }


    override fun onStart() {
        googleApiClient?.connect()
        if(googleApiClient!!.isConnecting){
            Log.e("AAAA","google client is connected!!!")
        }else{
            Log.e("AAAA","google client not connected!!!")
        }
    }

    override fun onStop() {
        if (googleApiClient!!.isConnecting) {
            googleApiClient?.disconnect()
        }
    }

    override fun onConnected(p0: Bundle?) {
        if (checkPermission()) return
        startLocationUpdates()
        if(googleApiClient!!.isConnecting){
            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)
            startLocationUpdates()
            updateVariables(location)
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onLocationChanged(location: Location) {
        val msg = "Updated Location: " +
                (location.latitude).toString() + "," +
                (location.longitude).toString()

        updateVariables(location)

        activity.latitude_longtude.setTextColor(Color.GREEN)

        activity.latitude_longtude.text =
                Location.convert(location.latitude, Location.FORMAT_SECONDS) +
                "°с.ш " + Location.convert(location.longitude, Location.FORMAT_SECONDS) + "°в.д"

        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    private fun updateVariables(location: Location) {
        latitude = location.latitude
        longtude = location.longitude
    }

    private fun startLocationUpdates() {
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL)

        if (checkPermission()) {
            return
        }
        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,
                    locationRequest, this)
        }
        activity.hideProgress()

    }

    private fun requestLocation() {
        RxPermissions(activity)
                .request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(object : Observer<Boolean> {
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onError(e: Throwable) {}
                    override fun onNext(t: Boolean) {
                        if (t) {
                            Log.e("requestLocation()", "granted!")
                            startLocationUpdates()
                        } else {
                            Log.e("requestLocation()", "not granted")
                            Toast.makeText(activity, "Нет информации о местоположении",
                                    Toast.LENGTH_LONG).show()
                            activity.applyDefaultCoordinates()
                        }
                    }
                })
    }

    private fun checkPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }

    private fun checkLocation(): Boolean {
        if (!isLocationEnabled())
            Toast.makeText(activity, "Местоположение не обнаружено.", Toast.LENGTH_SHORT).show()
        return isLocationEnabled()
    }

    private fun isLocationEnabled(): Boolean {
        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onConnectionSuspended(i: Int) {
        Log.e("AAAAA", "Приостановлено")
        googleApiClient!!.connect()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.e("AAAAA", "Подключение нарушено. Ошибка: " + connectionResult.errorCode)
    }
}
