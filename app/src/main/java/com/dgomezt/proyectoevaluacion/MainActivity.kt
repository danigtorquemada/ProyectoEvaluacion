package com.dgomezt.proyectoevaluacion

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnticipateInterpolator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgomezt.proyectoevaluacion.data.FootballService
import com.dgomezt.proyectoevaluacion.data.response.ResponseOf
import com.dgomezt.proyectoevaluacion.data.team.ResponseTeam
import com.dgomezt.proyectoevaluacion.recycleview.adapters.TeamAdapter
import com.dgomezt.proyectoevaluacion.recycleview.adapters.TeamAdapter.OnTeamsClickListener
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var _service: FootballService

    private var _responsesTeam = ArrayList<ResponseTeam>()
    private lateinit var _teamAdapter: TeamAdapter


    companion object {
        const val SEASON = 2022
        const val LEAGUE = 140
        private const val COLUMNS = 3
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splashConfig()

        /*     Para poder meter la API Key en la cabecera de todas las peticiones
        Si quisieramos poner solamente en una peticion podriamos usar la anotacion @Header
        Ejemplo:
        @Headers("X-RapidAPI-Key: e693df9d24msh804f774f3441c48p197f9fjsnb452a3614706",
        "X-RapidAPI-Host: api-football-beta.p.rapidapi.com")    */
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


        val recyclerView = findViewById<RecyclerView>(R.id.teams_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, COLUMNS)

        _teamAdapter = TeamAdapter(_responsesTeam, object : OnTeamsClickListener {
            override fun onTeamsClick(responseTeam: ResponseTeam) {
                val intent = Intent()
                intent.setClass(this@MainActivity, PlayerDetailsActivity::class.java)
                intent.putExtra(PlayerDetailsActivity.TEAM_KEY, responseTeam.team.id.toString())
                startActivity(intent)
            }
        })

        recyclerView.adapter = _teamAdapter

        loadTeams()
    }

    private fun splashConfig() {
        splashScreen.setOnExitAnimationListener { view ->
            view.iconView?.let { icon ->
                // set an animator that goes from height to 0, it will use AnticipateInterpolator set at the bottom of this code
                val animator = ValueAnimator
                    .ofInt(icon.height, 0)
                    .setDuration(2000)
                //update the icon height and width every time the animator value change
                animator.addUpdateListener {
                    val value = it.animatedValue as Int
                    icon.layoutParams.width = value
                    icon.layoutParams.height = value
                    icon.requestLayout()
                    if (value == 0) {
                        view.remove()
                    }
                }
                val animationSet = AnimatorSet()
                animationSet.interpolator = AnticipateInterpolator()
                // Default tension of AnticipateInterpolator is 2,
                // this means that the animation will increase first the size of the icon a little bit and then decrease
                animationSet.play(animator)
                animationSet.start() // Launch the animation
            }
            /* ****** ANIMATION TRANSLATION SPLASH SCREEN ******** */
            //splashScreenView ->
            //// Create your custom animation.
            //val slideUp = ObjectAnimator.ofFloat(
            //    splashScreenView,
            //    View.TRANSLATION_X,
            //    0f,
            //    -splashScreenView.width.toFloat()
            //)
            //slideUp.interpolator = AnticipateInterpolator()
            //slideUp.duration = 2000L

            //// Call SplashScreenView.remove at the end of your custom animation.
            //slideUp.doOnEnd { splashScreenView.remove() }

            //// Run your animation.
            //slideUp.start()*/
            /* ******************************************** */
        }
    }

    private fun loadTeams() {
        val call = _service.getTeams(LEAGUE, SEASON)
        call.enqueue(object : Callback<ResponseOf<ResponseTeam>> {
            override fun onResponse(
                call: Call<ResponseOf<ResponseTeam>>,
                response: Response<ResponseOf<ResponseTeam>>
            ) {
                if (response.isSuccessful) {
                    val responses = response.body()!!.response
                    _responsesTeam.addAll(responses)
                    _teamAdapter.notifyItemRangeInserted(0, _responsesTeam.size)
                }
            }

            override fun onFailure(call: Call<ResponseOf<ResponseTeam>>, t: Throwable) {
                t.localizedMessage?.let { Log.i(MainActivity::class.java.name, it) }
            }
        })
    }

}