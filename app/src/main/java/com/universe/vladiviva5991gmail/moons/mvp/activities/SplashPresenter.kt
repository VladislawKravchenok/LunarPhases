package com.universe.vladiviva5991gmail.moons.mvp.activities

import android.util.Log
import com.universe.vladiviva5991gmail.moons.domain.entity.MoonEntity
import com.universe.vladiviva5991gmail.moons.domain.usecase.GetMoon
import com.universe.vladiviva5991gmail.moons.mvp.App
import com.universe.vladiviva5991gmail.moons.mvp.activities.base.BasePresenter
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SplashPresenter : BasePresenter() {
    override fun createInject() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var getMoon: GetMoon

    init{
        getMoon.get().subscribe(object: Observer<MoonEntity>{
            override fun onComplete() {}

            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: MoonEntity) {
                Log.e("State moon right now",t.age.toString())
                Log.e("State moon right now",t.stage)
                Log.e("State moon right now",t.illumination.toString())
                Log.e("State moon right now",t.dfs.toString())
                Log.e("State moon right now",t.dfcoe.toString())

            }

            override fun onError(e: Throwable) {}
        })

    }
}