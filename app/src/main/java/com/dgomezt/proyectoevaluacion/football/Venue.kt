package com.dgomezt.proyectoevaluacion.football

import java.net.Inet4Address

class Venue(var name : String,
var address: String) {
    override fun toString(): String {
        return "Venue(name='$name', address='$address')"
    }
}