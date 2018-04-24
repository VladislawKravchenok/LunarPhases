package com.universe.vladiviva5991gmail.moons.mvp


class AppConstants {
    companion object {
        const val SYNODIC_MONTH: Int = 30

        /**The coordinates specified in the application by default,
         * if the user does not approve the request for his location*/
        const val GREENWICH_DEFOULT_COORDINATES_LATITUDE: String = "51:28:36.6672"
        const val GREENWICH_DEFOULT_COORDINATES_LONGITUDE: String = "-0:0:1.8000"

        /**Constats for location update in RequestLocation.class*/
        const val UPDATE_INTERVAL = (30 * 1000).toLong()  /* 30 sec */
        const val FASTEST_INTERVAL: Long = 2000 /* 2 sec */


    }
}