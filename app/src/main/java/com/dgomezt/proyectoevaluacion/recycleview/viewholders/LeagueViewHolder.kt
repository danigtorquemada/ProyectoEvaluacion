package com.dgomezt.proyectoevaluacion.recycleview.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dgomezt.proyectoevaluacion.R
import com.dgomezt.proyectoevaluacion.data.league.ResponseLeague

class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val _nameTextView = itemView.findViewById<TextView>(R.id.league_name_text)

    fun bind(responseLeague: ResponseLeague){
    _nameTextView.text = responseLeague.league.name
    }
}