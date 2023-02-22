package com.dgomezt.proyectoevaluacion.data.player

class Player (var firstname: String, var lastname : String, var nationality : String, var photo : String) {
    override fun toString(): String {
        return "Player(firstname='$firstname', lastname='$lastname', nationality='$nationality', photo='$photo')"
    }
}