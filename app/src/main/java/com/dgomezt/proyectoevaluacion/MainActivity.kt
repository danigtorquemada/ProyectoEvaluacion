package com.dgomezt.proyectoevaluacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dgomezt.proyectoevaluacion.data.FootballService
import com.dgomezt.proyectoevaluacion.data.ResponseOf
import com.dgomezt.proyectoevaluacion.data.league.ResponseLeague
import com.dgomezt.proyectoevaluacion.data.team.ResponseTeam
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Headers

class MainActivity() : AppCompatActivity() {
    lateinit var _service : FootballService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*     Para poder meter la API Key en la cabecera de todas las peticiones
        Si quisieramos poner solamente en una peticion podriamos usar la anotacion @Header
        Ejemplo:
        @Headers("X-RapidAPI-Key: e693df9d24msh804f774f3441c48p197f9fjsnb452a3614706",
        "X-RapidAPI-Host: api-football-beta.p.rapidapi.com")    */
        var okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-RapidAPI-Key", "e693df9d24msh804f774f3441c48p197f9fjsnb452a3614706")
                    builder.header("X-RapidAPI-Host", "api-football-beta.p.rapidapi.com")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-football-beta.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        _service = retrofit.create(FootballService::class.java)

        loadLeagues()
    }

    private fun loadLeagues() {
        var call = _service.getAllLeagues("league", "2022")
        call.enqueue(object : Callback<ResponseOf<ResponseLeague>>{
            override fun onResponse(
                call: Call<ResponseOf<ResponseLeague>>,
                response: Response<ResponseOf<ResponseLeague>>
            ) {
                if(response.isSuccessful)
                Log.i(MainActivity::class.java.name, response.body().toString())
            }

            override fun onFailure(call: Call<ResponseOf<ResponseLeague>>, t: Throwable) {
                Log.i(MainActivity::class.java.name, "ERROOOOOR")
            }

        })
    }

    fun loadTeam(id : Int, ){
        var call = _service.getTeam(id)
        call.enqueue(object : Callback<ResponseOf<ResponseTeam>> {
            override fun onResponse(call: Call<ResponseOf<ResponseTeam>>, responseOf: Response<ResponseOf<ResponseTeam>>) {
                if(responseOf.isSuccessful)
                    Log.i(MainActivity::class.java.name, responseOf.body().toString())
            }

            override fun onFailure(call: Call<ResponseOf<ResponseTeam>>, t: Throwable) {
                Log.i(MainActivity::class.java.name, "ERROOOOOR")
        }})
    }
}