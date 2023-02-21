package com.dgomezt.proyectoevaluacion.football

import java.net.URI

class Team(
    var name: String,
    var country: String,
    var logo : URI
) {
    override fun toString(): String {
        return "Team(name='$name', country='$country', logo=$logo)"
    }
}
