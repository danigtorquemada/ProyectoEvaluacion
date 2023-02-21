package com.dgomezt.proyectoevaluacion.football

class ResponseTeam(var team : Team, var venue: Venue) {
    override fun toString(): String {
        return "Respuesta(team=$team, venue=$venue)"
    }
}