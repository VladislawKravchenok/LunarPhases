package com.universe.vladiviva5991gmail.moons.mvp.activities.calculations

import android.util.Log
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

object DateConverter {
    @JvmStatic
    fun engToRus(date: Date):String {
        val format = SimpleDateFormat("dd MMM. yyyy z")
        Log.e("AAAAA", format.format(date).toString())
        return format.format(date).toString()
    }

    @JvmStatic
    val dateFormatSymbols = object : DateFormatSymbols() {
        override fun getMonths(): Array<String> = arrayOf("янв", "фев", "мар", "апр", "мая", "июня",
                "июля", "авг", "сен", "окт", "нояб", "дек")

    }
}