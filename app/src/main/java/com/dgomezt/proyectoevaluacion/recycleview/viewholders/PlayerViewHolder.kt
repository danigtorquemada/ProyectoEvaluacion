package com.dgomezt.proyectoevaluacion.recycleview.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dgomezt.proyectoevaluacion.R
import com.dgomezt.proyectoevaluacion.data.player.ResponsePlayer
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class PlayerViewHolder(itemView: View) : ViewHolder(itemView) {
    private val _firstNameTextView : TextView = itemView.findViewById(R.id.player_firstname_text_view)
    private val _secondNameTextView : TextView = itemView.findViewById(R.id.player_secondname_text_view)
    private val _nationalityTextView : TextView = itemView.findViewById(R.id.player_nacionality_text_view)
    private val _imageView : ImageView = itemView.findViewById(R.id.player_image_view)

    fun bind(responsePlayer: ResponsePlayer){
        _firstNameTextView.text = responsePlayer.player.firstname
        _secondNameTextView.text = responsePlayer.player.lastname
        _nationalityTextView.text = responsePlayer.player.nationality

        Picasso.get().load(responsePlayer.player.photo.toString())
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .into(_imageView);
    }
}