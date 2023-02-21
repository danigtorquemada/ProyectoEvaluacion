package com.dgomezt.proyectoevaluacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dgomezt.proyectoevaluacion.football.FootballService
import com.dgomezt.proyectoevaluacion.football.ResponseTeam
import com.dgomezt.proyectoevaluacion.football.Respuesta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity() : AppCompatActivity() {
    lateinit var _service : FootballService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder().baseUrl("https://api-football-beta.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create()).build()
        _service = retrofit.create(FootballService::class.java)

        loadTeam(10)
    }

    fun loadTeam(id : Int, ){
        var call = _service.getTeam(id)
        call.enqueue(object : Callback<Respuesta> {
            override fun onResponse(call: Call<Respuesta>, response: Response<Respuesta>) {
                if(response.isSuccessful)
                    Log.i(MainActivity::class.java.name, response.body().toString())
            }

            override fun onFailure(call: Call<Respuesta>, t: Throwable) {
                Log.i(MainActivity::class.java.name, "ERROOOOOR")
        }})
    }
}