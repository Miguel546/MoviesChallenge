package com.luismiguel.movieschallenge.data.network.datastatus

class DataStatusRemote<out T>(val status: Status, val code: Int = -1, val data: T? = null, val message: String? = null) {
    enum class Status{
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <T> loading(): DataStatusRemote<T> {
            return DataStatusRemote(Status.LOADING)
        }

        fun <T> success(code: Int, data: T?): DataStatusRemote<T> {
            return DataStatusRemote(Status.SUCCESS, code, data)
        }

        fun <T> error(code: Int, error: String): DataStatusRemote<T> {
            return DataStatusRemote(Status.ERROR, code = code, message = error)
        }

    }
}