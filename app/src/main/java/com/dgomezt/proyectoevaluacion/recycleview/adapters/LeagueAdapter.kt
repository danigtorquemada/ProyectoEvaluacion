package com.dgomezt.proyectoevaluacion.recycleview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dgomezt.proyectoevaluacion.R
import com.dgomezt.proyectoevaluacion.data.league.ResponseLeague
import com.dgomezt.proyectoevaluacion.recycleview.viewholders.LeagueViewHolder

class LeagueAdapter(private val _responsesLeague: List<ResponseLeague>) : RecyclerView.Adapter<LeagueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.league_row, parent, false)

        return LeagueViewHolder(view)
    }

    override fun getItemCount(): Int {
        return _responsesLeague.size
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(_responsesLeague[position])
    }
}