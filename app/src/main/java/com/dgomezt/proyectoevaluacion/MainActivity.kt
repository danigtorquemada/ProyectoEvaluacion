package com.dgomezt.proyectoevaluacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgomezt.proyectoevaluacion.data.FootballService
import com.dgomezt.proyectoevaluacion.data.ResponseOf
import com.dgomezt.proyectoevaluacion.data.team.ResponseTeam
import com.dgomezt.proyectoevaluacion.recycleview.adapters.TeamAdapter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity() : AppCompatActivity() {
    private lateinit var _service: FootballService

    private var _responsesTeam = ArrayList<ResponseTeam>()
    private lateinit var _teamAdapter : TeamAdapter

    private val COLUMNS = 3

    private val SEASON = 2022
    private val LEAGUE = 140

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


        var recyclerView = findViewById<RecyclerView>(R.id.teams_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, COLUMNS)
        _teamAdapter =  TeamAdapter(_responsesTeam)
        recyclerView.adapter = _teamAdapter

        loadLeagues()
    }

    private fun loadLeagues() {
        var call = _service.getTeams(LEAGUE,SEASON)
        call.enqueue(object : Callback<ResponseOf<ResponseTeam>> {
            override fun onResponse(
                call: Call<ResponseOf<ResponseTeam>>,
                response: Response<ResponseOf<ResponseTeam>>
            ) {
                if (response.isSuccessful){
                    var responses  = response.body()!!.response
                    _responsesTeam.addAll(responses)
                    _teamAdapter.notifyItemRangeInserted(0, 20)
                }
            }

            override fun onFailure(call: Call<ResponseOf<ResponseTeam>>, t: Throwable) {
                Log.i(MainActivity::class.java.name, t.localizedMessage)
            }
        })
    }
}