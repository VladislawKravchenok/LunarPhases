package com.universe.vladiviva5991gmail.moons.mvp


class AppConstants {
    companion object {
        const val SYNODIC_MONTH: Float = 29.53058868F

        /**The coordinates specified in the application by default,
         * if the user does not approve the request for his location*/
        const val GREENWICH_DEFOULT_COORDINATES_LATITUDE: Double = 51.476852
        const val GREENWICH_DEFOULT_COORDINATES_LONGITUDE: Double = -0.000500

        /**Constats for location update in RequestLocation.class*/
        const val UPDATE_INTERVAL = (30 * 1000).toLong()  /* 30 sec */
        const val FASTEST_INTERVAL: Long = 2000 /* 2 sec */


    }
}