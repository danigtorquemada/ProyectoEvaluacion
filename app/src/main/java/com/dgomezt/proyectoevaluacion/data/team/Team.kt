package com.dgomezt.proyectoevaluacion.data.team

import java.net.URI

class Team(var name: String, var id: Int, var logo : URI) {
    override fun toString(): String {
        return "Team(name='$name', id=$id, logo=$logo)"
    }
}
