package com.dgomezt.proyectoevaluacion.data

import com.dgomezt.proyectoevaluacion.data.league.ResponseLeague
import com.dgomezt.proyectoevaluacion.data.team.ResponseTeam
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballService {
    @GET("teams")
    fun getTeam(@Query("id") id : Int) : Call<ResponseOf<ResponseTeam>>

    @GET("leagues")
    fun getAllLeagues(@Query("type") type : String, @Query("season") season : String) : Call<ResponseOf<ResponseLeague>>
}