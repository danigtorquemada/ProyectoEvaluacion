package com.dgomezt.proyectoevaluacion.data.league

class ResponseLeague (var league : League, var country: Country) {
    override fun toString(): String {
        return "ResponseLeague(league=$league, country=$country)"
    }
}