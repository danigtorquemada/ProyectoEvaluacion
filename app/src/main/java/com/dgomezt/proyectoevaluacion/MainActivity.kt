package com.dgomezt.proyectoevaluacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgomezt.proyectoevaluacion.data.FootballService
import com.dgomezt.proyectoevaluacion.data.ResponseOf
import com.dgomezt.proyectoevaluacion.data.league.ResponseLeague
import com.dgomezt.proyectoevaluacion.data.team.ResponseTeam
import com.dgomezt.proyectoevaluacion.recycleview.adapters.LeagueAdapter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity() : AppCompatActivity() {
    private lateinit var _service: FootballService

    private var _responsesLeague = ArrayList<ResponseLeague>()
    private lateinit var _leagueAdapter : LeagueAdapter

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
                    builder.header(
                        "X-RapidAPI-Key",
                        "e693df9d24msh804f774f3441c48p197f9fjsnb452a3614706"
                    )
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


        var recyclerView = findViewById<RecyclerView>(R.id.leagues_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        _leagueAdapter =  LeagueAdapter(_responsesLeague)
        recyclerView.adapter = _leagueAdapter

        loadLeagues()
    }

    private fun loadLeagues() {
        var call = _service.getAllLeagues("league", "2022")
        call.enqueue(object : Callback<ResponseOf<ResponseLeague>> {
            override fun onResponse(
                call: Call<ResponseOf<ResponseLeague>>,
                response: Response<ResponseOf<ResponseLeague>>
            ) {
                if (response.isSuccessful){
                    var responses  = response.body()!!.response
                    _responsesLeague.addAll(responses)
                    _leagueAdapter.notifyItemInserted(0)
                }
            }

            override fun onFailure(call: Call<ResponseOf<ResponseLeague>>, t: Throwable) {
                Log.i(MainActivity::class.java.name, "ERROOOOOR")
            }
        })
    }
}