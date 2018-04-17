package com.universe.vladiviva5991gmail.moons.data.net

import com.universe.vladiviva5991gmail.moons.data.entity.Moon
import io.reactivex.Flowable
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface RestApi{

    @GET("/moon/")
    fun loadMoonStatus():Flowable<Moon>
}