package com.universe.vladiviva5991gmail.moons

import com.universe.vladiviva5991gmail.moons.mvp.activities.calculations.MoonPhase
import org.junit.Assert
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testLunarNumber() {
        val a = 7
        Assert.assertEquals(a,MoonPhase.getLunarNumber(2001))

    }

    @Test
    fun testMoonAge(){
        val a = 6
        val b = 5.9388237f
        Assert.assertEquals(a,MoonPhase.getMoonAge(5,20,4))
        Assert.assertEquals(b,MoonPhase.getMoonAge(5,20,4))

    }
}
