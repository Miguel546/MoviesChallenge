package com.luismiguel.movieschallenge.data.network.datastatus

class DataStatusLocal <out T>(val status: Status, val data: T? = null, val message: String? = null) {
    enum class Status{
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <T> loading(): DataStatusLocal<T> {
            return DataStatusLocal(Status.LOADING)
        }

        fun <T> success(data: T?): DataStatusLocal<T> {
            return DataStatusLocal(Status.SUCCESS, data)
        }

        fun <T> error(error: String): DataStatusLocal<T> {
            return DataStatusLocal(Status.ERROR, message = error)
        }

    }
}