package com.universe.vladiviva5991gmail.moons.domain.usecase

import com.universe.vladiviva5991gmail.moons.domain.entity.MoonEntity
import com.universe.vladiviva5991gmail.moons.domain.executor.PostExecutionThread
import com.universe.vladiviva5991gmail.moons.domain.repository.MoonRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoon
@Inject
constructor(postExecutionThread: PostExecutionThread, private val moonRepository: MoonRepository)
    : BaseUseCase(postExecutionThread) {


    fun get():Observable<MoonEntity>{
        return moonRepository
                .get()
                .subscribeOn(threadExecution)//в каком потоке запустить
                .observeOn(postExecutionThread)//в каком потоке получить
    }
}