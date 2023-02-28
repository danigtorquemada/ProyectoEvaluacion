package com.dgomezt.proyectoevaluacion.data.response

class Paging (var current : Int, var total : Int) {
    override fun toString(): String {
        return "Paging(current=$current, total=$total)"
    }
}