package com.universe.vladiviva5991gmail.moons.domain.usecase

import com.universe.vladiviva5991gmail.moons.domain.executor.PostExecutionThread
import com.universe.vladiviva5991gmail.moons.domain.executor.ThreadExecution
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase {
    //поток в котором будем получать результаты в presentatin слое
    var postExecutionThread: Scheduler

    //поток в котором будем выполнять всесложные запросы(это рабочий поток, не UI)
    var threadExecution: Scheduler

    constructor(postExecutionThread: PostExecutionThread, threadExecution: ThreadExecution) {
        this.postExecutionThread = postExecutionThread.scheduler
        this.threadExecution = Schedulers.from(threadExecution)
    }

    constructor(postExecutionThread: PostExecutionThread) {
        this.postExecutionThread = postExecutionThread.scheduler
        this.threadExecution = Schedulers.io()
    }
}