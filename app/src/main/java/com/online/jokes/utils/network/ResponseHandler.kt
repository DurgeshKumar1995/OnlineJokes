package com.online.jokes.utils.network

import com.online.jokes.R
import com.online.jokes.domain.StringProvideUseCase
import com.online.jokes.utils.NetworkException
import retrofit2.HttpException
import java.net.SocketTimeoutException

object ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception, spUseCase: StringProvideUseCase): Resource<T> {
        return when (e) {
            is NetworkException -> Resource.error(
                getErrorMessage(
                    ErrorCodes.NetworkConnection.code,
                    spUseCase,
                ),
            )

            is HttpException -> Resource.error(getErrorMessage(e.code(), spUseCase))
            is SocketTimeoutException -> Resource.error(
                getErrorMessage(
                    ErrorCodes.SocketTimeOut.code,
                    spUseCase,
                ),
            )

            else -> Resource.error(getErrorMessage(Int.MAX_VALUE, spUseCase))
        }
    }

    fun <T : Any> handleLoading(): Resource<T> {
        return Resource.loading()
    }

    private fun getErrorMessage(code: Int, spUseCase: StringProvideUseCase): String {
        val stringId = when (code) {
            ErrorCodes.SocketTimeOut.code -> R.string.time_out_issue
            ErrorCodes.NetworkConnection.code -> R.string.network_issue
            401 -> R.string.unauthorised_issue
            404 -> R.string.not_found
            else -> R.string.something_else
        }

        return spUseCase.invoke(stringId)
    }
}
