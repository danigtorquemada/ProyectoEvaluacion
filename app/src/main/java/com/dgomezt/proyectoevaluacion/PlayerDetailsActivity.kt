package com.dgomezt.proyectoevaluacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.dgomezt.proyectoevaluacion.data.FootballService
import com.dgomezt.proyectoevaluacion.data.player.ResponsePlayer
import com.dgomezt.proyectoevaluacion.data.response.ResponseOf
import com.dgomezt.proyectoevaluacion.recycleview.adapters.PlayerAdapter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlayerDetailsActivity : AppCompatActivity() {

    private lateinit var _service: FootballService
    private lateinit var _playerAdapter : PlayerAdapter

    private var _responsePlayer = ArrayList<ResponsePlayer>()

    private var _offset = 0

    private var page = 1
    private var moreElements = true
    private var isLoading = false

    companion object {
        const val TEAM_KEY : String = "TEAM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)

        val team = intent.getStringExtra(TEAM_KEY)

        val okHttpClient = OkHttpClient.Builder().apply {
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


        val recyclerView = findViewById<RecyclerView>(R.id.players_recycler_view)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnScrollListener(object : OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
                if (moreElements && !isLoading) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                    ){
                        if (team != null) {
                            loadPlayers(team)
                            isLoading = true
                        }
                    }
                }
            }
        })

        _playerAdapter =  PlayerAdapter(_responsePlayer)

        recyclerView.adapter = _playerAdapter

        if (team != null) {
            loadPlayers(team)
        }
    }

    private fun loadPlayers(_team : String) {
        val call = _service.getPlayersByTeam(MainActivity.LEAGUE,MainActivity.SEASON, _team, page)
        call.enqueue(object : Callback<ResponseOf<ResponsePlayer>> {
            override fun onResponse(
                call: Call<ResponseOf<ResponsePlayer>>,
                response: Response<ResponseOf<ResponsePlayer>>
            ) {
                if (response.isSuccessful){
                    val responses  = response.body()!!.response
                    _responsePlayer.addAll(responses)
                    _playerAdapter.notifyItemRangeInserted(_offset, responses.size)
                    _offset += responses.size

                    if(++page > response.body()!!.paging.total){
                        moreElements = false
                    }

                    isLoading = false
                }
            }

            override fun onFailure(call: Call<ResponseOf<ResponsePlayer>>, t: Throwable) {
                t.localizedMessage?.let { Log.i(MainActivity::class.java.name, it) }
            }
        })
    }
}