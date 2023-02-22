package com.dgomezt.proyectoevaluacion.data.league

class Country (var name : String, var code : String, var flag : String){
    override fun toString(): String {
        return "Country(name='$name', code=$code, flag='$flag')"
    }
}