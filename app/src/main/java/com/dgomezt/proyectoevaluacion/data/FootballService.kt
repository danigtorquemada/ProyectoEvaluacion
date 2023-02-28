package com.dgomezt.proyectoevaluacion.data

import com.dgomezt.proyectoevaluacion.data.player.ResponsePlayer
import com.dgomezt.proyectoevaluacion.data.response.ResponseOf
import com.dgomezt.proyectoevaluacion.data.team.ResponseTeam
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballService {
    @GET("teams")
    fun getTeams(
        @Query("league") league: Int,
        @Query("season") season: Int
    ): Call<ResponseOf<ResponseTeam>>

    @GET("players")
    fun getPlayersByTeam(
        @Query("league") league: Int,
        @Query("season") season: Int,
        @Query("team") team: String,
        @Query("page") page: Int
    ): Call<ResponseOf<ResponsePlayer>>
}