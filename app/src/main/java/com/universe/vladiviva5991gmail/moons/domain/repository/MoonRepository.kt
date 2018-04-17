package com.universe.vladiviva5991gmail.moons.domain.repository

import com.universe.vladiviva5991gmail.moons.domain.entity.MoonEntity
import io.reactivex.Observable

interface MoonRepository {
    fun get(): Observable<MoonEntity>
}