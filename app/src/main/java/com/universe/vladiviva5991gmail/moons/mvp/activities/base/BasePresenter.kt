package com.universe.vladiviva5991gmail.moons.mvp.activities.base

import android.util.Log
import io.reactivex.annotations.Nullable

abstract class BasePresenter<View : BaseView, R : Router> : BaseInjection {
    constructor() : super() {
        createInject()
    }

    @Nullable
    protected lateinit var router: R
    @Nullable
    protected lateinit var view: View

    open fun actionWhenChooseDate(){}

    open fun attached(view: View, router: R) {
        this.router = router
        this.view = view
    }

    open fun dettached() {
        Log.e("AAAAA", "dettached")

    }

    open fun onResume() {}

    open fun onPause() {}

    open fun onStart() {}

    open fun onStop() {}

    open fun onDestriy() {}
}