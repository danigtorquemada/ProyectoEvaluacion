package com.dgomezt.proyectoevaluacion.recycleview.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dgomezt.proyectoevaluacion.R
import com.dgomezt.proyectoevaluacion.data.team.ResponseTeam
import com.dgomezt.proyectoevaluacion.recycleview.adapters.TeamAdapter
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class TeamViewHolder(itemView: View, _onTeamsClickListener: TeamAdapter.OnTeamsClickListener) :
    RecyclerView.ViewHolder(
        itemView
    ) {

    private val _nameTextView: TextView
    private val _imageView: ImageView
    private lateinit var _responseTeam:  ResponseTeam

    init {
        this._nameTextView = itemView.findViewById(R.id.team_name_text)
        this._imageView = itemView.findViewById(R.id.team_image_view)
        _imageView.setOnClickListener {
            _onTeamsClickListener.onTeamsClick(_responseTeam)
        }
    }
    fun bind(responseTeam: ResponseTeam){
        _responseTeam = responseTeam
        _nameTextView.text = responseTeam.team.name

        Picasso.get().load(responseTeam.team.logo.toString()).resize(250,250)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .into(_imageView)
    }
}