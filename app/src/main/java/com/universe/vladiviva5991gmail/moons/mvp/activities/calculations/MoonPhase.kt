package com.universe.vladiviva5991gmail.moons.mvp.activities.calculations

import com.universe.vladiviva5991gmail.moons.mvp.AppConstants.Companion.SYNODIC_MONTH
import javax.inject.Singleton

//TODO 1) дописать методы для расчета восхода/захода 2)Дополнить формулы, для более точного расчета

@Singleton
class MoonPhase {

    companion object {

        @JvmStatic
        fun getLunarNumber(year: Int): Int {
            //TODO UnitTest
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
            //TODO Может быть погрешность в ~1 сутки
            val result = (lunarNumber * 11) - 14 + dayOfMonth + monthInYear
            return (result % SYNODIC_MONTH) - 0.46941132
        }

        @JvmStatic
        fun timeConversion(d: Double): String {
            val days = Math.floor(d).toInt()
            val rawRestHours = (d - days) * 24
            val hours = Math.floor(rawRestHours).toInt()
            val rawRestMinutes = (rawRestHours - hours) * 60
            val minutes = Math.floor(rawRestMinutes).toInt()
            val restSeconds = (rawRestMinutes - minutes) * 60
            return "" + days + "дней" + hours + "ч" + minutes + "м"
        }
    }


}

