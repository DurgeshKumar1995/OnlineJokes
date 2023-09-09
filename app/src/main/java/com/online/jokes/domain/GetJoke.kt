package com.online.jokes.domain

import com.online.jokes.data.model.JokeModel
import com.online.jokes.data.repo.JokeRepo
import com.online.jokes.utils.NetworkException
import com.online.jokes.utils.network.Resource
import com.online.jokes.utils.network.ResponseHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.TimeUnit

class GetJoke(
    private val repo: JokeRepo,
    private val dispatcher: CoroutineDispatcher,
    private val internetConnectionCase: InternetConnectionCase,
    private val stringProvideUseCase: StringProvideUseCase,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<Resource<JokeModel>> {
        return channelFlow {
            while (!isClosedForSend) {
                send(ResponseHandler.handleLoading())
                val data = try {
                    if (!internetConnectionCase.invoke()) {
                        throw NetworkException("Network issue")
                    }
                    val model = repo.getJoke().copy(time = System.currentTimeMillis())
                    ResponseHandler.handleSuccess(model)
                } catch (e: Exception) {
                    ResponseHandler.handleException(e, stringProvideUseCase)
                }
                send(data)
                kotlinx.coroutines.delay(TimeUnit.MINUTES.toMillis(1))
            }
        }.flowOn(dispatcher)
    }
}
