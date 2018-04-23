package com.universe.vladiviva5991gmail.moons.mvp.activities.base

import io.reactivex.annotations.Nullable
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<View : BaseView, R : Router> : BaseInjection {

    @Nullable
    protected lateinit var router: R
    @Nullable
    protected lateinit var view: View
    @Nullable
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()


    open fun attached(view: View, router: R) {
        this.router = router
        this.view = view
    }

    open fun dettached() {
        router = null!!
        view = null!!
    }

    open fun onResume() {}

    open fun onPause() {}

    open fun onStart() {}

    open fun onStop() {}

    open fun onDestriy() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}