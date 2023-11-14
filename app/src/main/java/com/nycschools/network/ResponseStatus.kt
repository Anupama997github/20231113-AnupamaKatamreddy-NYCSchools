package com.nycschools.network

data class ResponseStatus<out T>(val status: Status, val data: T?, val throwable: Throwable?) {
    companion object {
        fun <T> success(data: T) : ResponseStatus<T> {
            return ResponseStatus(Status.SUCCESS, data, null)
        }

        fun <T> error(throwable: Throwable?) : ResponseStatus<T> {
            return ResponseStatus(Status.FAILURE, null, throwable)
        }
    }
}

enum class Status {
    SUCCESS,
    FAILURE
}
