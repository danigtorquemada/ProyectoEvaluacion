package com.dgomezt.proyectoevaluacion.recycleview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dgomezt.proyectoevaluacion.R
import com.dgomezt.proyectoevaluacion.data.player.ResponsePlayer
import com.dgomezt.proyectoevaluacion.recycleview.viewholders.PlayerViewHolder

class PlayerAdapter(private val _responsePlayer: List<ResponsePlayer>) :
    RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.player_row, parent, false)

        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return _responsePlayer.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(_responsePlayer[position])
    }
}