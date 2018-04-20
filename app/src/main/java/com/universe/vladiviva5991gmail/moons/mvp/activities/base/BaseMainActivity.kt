package com.universe.vladiviva5991gmail.moons.mvp.activities.base

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tbruyelle.rxpermissions2.RxPermissions
import com.universe.vladiviva5991gmail.moons.R
import com.universe.vladiviva5991gmail.moons.mvp.AppConstants
import com.universe.vladiviva5991gmail.moons.mvp.location.BaseLocation
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseMainActivity<out Location : BaseLocation> : BaseActivity() {

    private lateinit var request: Location

    abstract fun provideLocation(): Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        request = provideLocation()
        //adb shell monkey -p com.universe.vla diviva5991gmail.moons -v 5000

        /**
         * запросить разрешение с RxPermission*/
        /*RxPermissions(this)
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(object : Observer<Boolean> {
                    override fun onComplete() {}

                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(t: Boolean) {
                        if (t) {//granted

                        } else {
                            //нет разрешение - выводим диалог
                        }
                    }

                    override fun onError(e: Throwable) {}
                })*/

    }

    override fun onResume() {
        super.onResume()
        request.onStart()
    }


    override fun onStop() {
        super.onStop()
        request.onStop()
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