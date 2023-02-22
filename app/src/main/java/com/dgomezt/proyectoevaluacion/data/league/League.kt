package com.dgomezt.proyectoevaluacion.data.league

class League(var name: String, var id : Int, var logo : String) {
    override fun toString(): String {
        return "League(name='$name', id=$id, logo='$logo')"
    }
}