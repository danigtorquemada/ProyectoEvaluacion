package com.dgomezt.proyectoevaluacion.recycleview.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dgomezt.proyectoevaluacion.R
import com.dgomezt.proyectoevaluacion.data.team.ResponseTeam

class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val _nameTextView = itemView.findViewById<TextView>(R.id.team_name_text)

    fun bind(responseLeague: ResponseTeam){
        _nameTextView.text = responseLeague.team.name
    }
}