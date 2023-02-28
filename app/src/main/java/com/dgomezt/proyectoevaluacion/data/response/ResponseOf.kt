package com.dgomezt.proyectoevaluacion.data.response

class ResponseOf<T>(var paging: Paging, var response : List<T>) {
    override fun toString(): String {
        return "ResponseOf(paging=$paging, response=$response)"
    }
}