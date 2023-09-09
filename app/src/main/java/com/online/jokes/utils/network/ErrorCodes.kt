package com.online.jokes.utils.network

sealed class ErrorCodes : ErrorCode {
    object SocketTimeOut : ErrorCodes() {
        override val code: Int
            get() = 500
    }

    object NetworkConnection : ErrorCodes() {
        override val code: Int
            get() = 999
    }
}

sealed interface ErrorCode {
    val code: Int
}
