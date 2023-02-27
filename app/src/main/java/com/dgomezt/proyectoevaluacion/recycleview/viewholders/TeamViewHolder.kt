package com.dgomezt.proyectoevaluacion.recycleview.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dgomezt.proyectoevaluacion.R
import com.dgomezt.proyectoevaluacion.data.team.ResponseTeam
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val _nameTextView = itemView.findViewById<TextView>(R.id.team_name_text)
    private val _imageView = itemView.findViewById<ImageView>(R.id.team_image_view)
    fun bind(responseTeam: ResponseTeam){
        _nameTextView.text = responseTeam.team.name

        Picasso.get().load(responseTeam.team.logo.toString())
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .into(_imageView);
    }
}