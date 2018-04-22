package com.universe.vladiviva5991gmail.moons.mvp.activities.base

import io.reactivex.annotations.Nullable
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<R : Router> : BaseInjection {

    @Nullable
    protected lateinit var router: R
    @Nullable
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    open fun attach(router: R) {
        this.router = router
    }

    open fun dettached() {
        router = null!!
    }

    open fun onResume() {}

    open fun onPause() {}

    open fun onStart() {}

    open fun onStop() {}

    open fun onDestriy() {
        if(!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}