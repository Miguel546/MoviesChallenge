package com.luismiguel.movieschallenge.data.network.dataresult

import retrofit2.Response

class DataResult<DATA>(val response: Response<DATA>) {
    var data: DATA? = null
    var error = Exception()

    init{
        when (response.code()) {

            in 200 .. 299 -> {
                if (response.body() is DATA?) {
                    val responseBody = response.body() as DATA
                    data = responseBody
                }
            }

            else -> {
                val errorBody = response.errorBody()?.string() ?: "Lo sentimos, ocurrió un error inesperado."
                error = DataResultException(errorBody, response.code())
            }
        }
    }
}