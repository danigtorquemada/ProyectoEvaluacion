package com.dgomezt.proyectoevaluacion.football

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FootballService {
    @Headers("X-RapidAPI-Key: e693df9d24msh804f774f3441c48p197f9fjsnb452a3614706",
            "X-RapidAPI-Host: api-football-beta.p.rapidapi.com")
    @GET("teams")
    fun getTeam(@Query("id") id : Int) : Call<Respuesta>
}