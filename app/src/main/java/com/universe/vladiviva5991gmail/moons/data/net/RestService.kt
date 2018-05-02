package com.universe.vladiviva5991gmail.moons.data.net

import com.universe.vladiviva5991gmail.moons.data.entity.Moon
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestService @Inject constructor(private val restApi: RestApi){
    fun loadMoonStatus():Flowable<Moon>{
        return restApi.loadMoonStatus()
    }
}