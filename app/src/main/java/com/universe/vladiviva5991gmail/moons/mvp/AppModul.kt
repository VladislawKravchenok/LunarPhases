package com.universe.vladiviva5991gmail.moons.mvp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.universe.vladiviva5991gmail.moons.BuildConfig
import com.universe.vladiviva5991gmail.moons.data.net.RestApi
import com.universe.vladiviva5991gmail.moons.data.net.RestService
import com.universe.vladiviva5991gmail.moons.data.repository.MoonRepositoryImpl
import com.universe.vladiviva5991gmail.moons.domain.executor.PostExecutionThread
import com.universe.vladiviva5991gmail.moons.domain.repository.MoonRepository
import com.universe.vladiviva5991gmail.moons.mvp.executor.UIThread
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModul constructor(private val context: Context) {
    @Provides
    @Singleton
    fun getContext(): Context = context

    @Provides
    @Singleton
    fun getUIThread(): PostExecutionThread = UIThread()

    @Provides
    @Singleton
    fun getMoonRepository(restService: RestService): MoonRepository = MoonRepositoryImpl(restService)

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient, gson: Gson)
            : Retrofit = Retrofit.Builder()
            .baseUrl("http://api.burningsoul.in/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun getRestApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)


    @Provides
    @Singleton
    fun getGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun getOkHttp(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val httpLogging = HttpLoggingInterceptor()
            httpLogging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLogging)
        }
        return builder.build()
    }
}