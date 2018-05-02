package com.universe.vladiviva5991gmail.moons.mvp.activities.calculations

import com.universe.vladiviva5991gmail.moons.mvp.AppConstants.Companion.SYNODIC_MONTH
import javax.inject.Singleton

@Singleton
class MoonPhase {
    companion object {
        @JvmStatic
        fun getLunarNumber(year: Int): Int {
            val startPoint = 2000
            var startPointNumber = 6
            var result = startPointNumber
            if (startPoint == year) {
                return startPointNumber
            } else if (startPoint < year) {
                result += year - startPoint
            } else if (startPoint > year) {
                result += startPoint - year
            }
            while (result > 19) {
                result -= 19
            }
            return result
        }

        @JvmStatic
        fun getMoonAge(lunarNumber: Int, dayOfMonth: Int, monthInYear: Int): Double {
            //TODO Может быть погрешность в ~one сутки
            val result = (lunarNumber * 11) - 14 + dayOfMonth + monthInYear
            return (result % SYNODIC_MONTH) + 0.53541132
        }

        @JvmStatic
        fun timeConversion(d: Double): String {
            val days = Math.floor(d).toInt()
            val rawRestHours = (d - days) * 24
            val hours = Math.floor(rawRestHours).toInt()
            val rawRestMinutes = (rawRestHours - hours) * 60
            val minutes = Math.floor(rawRestMinutes).toInt()
            val restSeconds = (rawRestMinutes - minutes) * 60
            return "" + days + dayConverter(days) + hours + "ч" + minutes + "м"
        }

        @JvmStatic
        fun dayConverter(days: Int): String = when (days) {
            1, 21 -> "день"
            in 2..4, in 22..24 -> "дня"
            in 5..20, in 25..30 -> "дней"
            else -> "дней"
        }
    }
}

