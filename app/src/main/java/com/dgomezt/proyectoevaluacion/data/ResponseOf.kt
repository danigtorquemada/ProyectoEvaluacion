package com.dgomezt.proyectoevaluacion.data

class ResponseOf<T>(var response : List<T>) {
    override fun toString(): String {
        return "Response($response)"
    }
}