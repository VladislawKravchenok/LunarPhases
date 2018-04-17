package com.universe.vladiviva5991gmail.moons.mvp.activities.base

abstract class BasePresenter : BaseInjection {
    constructor() : super() {
        createInject()
    }


    open fun onResume() {}

    open fun onPause() {}

    open fun onStart() {}

    open fun onStop() {}
}