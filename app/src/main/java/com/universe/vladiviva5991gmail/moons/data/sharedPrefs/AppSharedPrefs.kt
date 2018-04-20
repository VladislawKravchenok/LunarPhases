package com.universe.vladiviva5991gmail.moons.data.sharedPrefs

import android.content.Context
import javax.inject.Inject


//TODO Класс не готов
//Класс для проверки при первом запуске
class AppSharedPrefs @Inject constructor(private val context: Context) {
    //для таких простых классов не стоит создавать UseCase
    companion object {
        @JvmStatic
        private var token: String? = null
            get() {
                return if (field != null) field else field //else {читаем изAppSharedPrefs
                //и возвращаем, сделали типо кэширования что бы постоянно не читали каждый раз xml, при
                //множественных запросах
            }

    }


    fun saveTipsShow() {}
    fun getTipsShow(): Boolean {
        return false
    }
//токен(ответ сервера - авторизация) - так же следует записывать в SharedPrefs
//лучше сделать 2 SharedPrefs класса один для токенов, другой для первого запуска
}