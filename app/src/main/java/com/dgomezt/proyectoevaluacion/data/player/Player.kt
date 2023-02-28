package com.dgomezt.proyectoevaluacion.data.player

import java.net.URI

class Player (var firstname: String, var lastname : String, var nationality : String, var photo : URI) {
    override fun toString(): String {
        return "Player(firstname='$firstname', lastname='$lastname', nationality='$nationality', photo='$photo')"
    }
}