package com.universe.vladiviva5991gmail.moons.mvp

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModul constructor(private val context: Context) {

    @Provides
    @Singleton
    fun getContext(): Context = context


}