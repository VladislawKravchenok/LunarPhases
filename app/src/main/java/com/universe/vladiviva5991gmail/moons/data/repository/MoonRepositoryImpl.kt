package com.universe.vladiviva5991gmail.moons.data.repository

import com.universe.vladiviva5991gmail.moons.data.net.RestService
import com.universe.vladiviva5991gmail.moons.domain.entity.MoonEntity
import com.universe.vladiviva5991gmail.moons.domain.repository.MoonRepository
import io.reactivex.Observable
import javax.inject.Inject

class MoonRepositoryImpl
@Inject
constructor(val restService: RestService) : MoonRepository{


    override fun get(): Observable<MoonEntity> {

        return restService
                .loadMoonStatus()
                .map { MoonEntity(it.age,it.illumination,it.stage,it.dfcoe,it.dfs) }
                .toObservable()

    }
}