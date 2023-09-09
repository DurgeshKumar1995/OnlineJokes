package com.online.jokes.data.impl

import com.online.jokes.data.model.JokeModel
import com.online.jokes.data.network.RestApi
import com.online.jokes.data.repo.JokeRepo

class JokeImpl(private val restApi: RestApi) : JokeRepo {
    override suspend fun getJoke(): JokeModel = restApi.getJoke()
}
