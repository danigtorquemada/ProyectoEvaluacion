package com.dgomezt.proyectoevaluacion.recycleview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dgomezt.proyectoevaluacion.R
import com.dgomezt.proyectoevaluacion.data.team.ResponseTeam
import com.dgomezt.proyectoevaluacion.recycleview.viewholders.TeamViewHolder

class TeamAdapter(private val _responsesTeam: List<ResponseTeam>) :
    RecyclerView.Adapter<TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.team_row, parent, false)

        return TeamViewHolder(view)
    }

    override fun getItemCount(): Int {
        return _responsesTeam.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(_responsesTeam[position])
    }
}