package com.online.jokes.utils.network

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        fun <T> loading(msg: String = "Loading"): Resource<T> {
            return Resource(Status.LOADING, null, msg)
        }
    }
}
