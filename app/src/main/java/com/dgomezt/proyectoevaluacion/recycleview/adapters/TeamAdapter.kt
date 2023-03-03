package com.dgomezt.proyectoevaluacion.recycleview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dgomezt.proyectoevaluacion.R
import com.dgomezt.proyectoevaluacion.data.team.ResponseTeam
import com.dgomezt.proyectoevaluacion.recycleview.viewholders.TeamViewHolder

class TeamAdapter(private val _responsesTeam: List<ResponseTeam>, private val _onTeamsClickListener : OnTeamsClickListener) :
    RecyclerView.Adapter<TeamViewHolder>() {

    interface OnTeamsClickListener {
        fun onTeamsClick(responseTeam: ResponseTeam)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_element, parent, false)

        return TeamViewHolder(view, _onTeamsClickListener)
    }

    override fun getItemCount(): Int {
        return _responsesTeam.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(_responsesTeam[position])
    }
}