package com.universe.vladiviva5991gmail.moons.mvp.executor

import com.universe.vladiviva5991gmail.moons.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UIThread
@Inject
constructor():PostExecutionThread{
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}