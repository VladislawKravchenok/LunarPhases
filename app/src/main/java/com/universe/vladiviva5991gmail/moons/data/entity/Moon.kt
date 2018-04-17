package com.universe.vladiviva5991gmail.moons.data.entity

import com.google.gson.annotations.SerializedName


data class Moon(
        val age: Double,
        val illumination: Double,
        val stage: String,
        @SerializedName("DFCOE")
        val dfcoe: Double,
        @SerializedName("DFS")
        val dfs: Double)
        /*val fm: FM,
        val nnm: NNM){
        inner class FM(@SerializedName("DT") val date: Double)//дата и время полной луны
        inner class NNM(@SerializedName("DT") val date: Double)//дата и время полной луны
}
*/
