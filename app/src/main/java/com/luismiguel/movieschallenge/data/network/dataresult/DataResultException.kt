package com.luismiguel.movieschallenge.data.network.dataresult

import java.lang.Exception

class DataResultException(
    val mensaje: String ?= "",
    val code: Int ?= 0
): Exception("Código: $code\nMensaje: $mensaje")