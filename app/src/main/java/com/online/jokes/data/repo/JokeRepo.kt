package com.online.jokes.data.repo

import com.online.jokes.data.model.JokeModel

interface JokeRepo {
    suspend fun getJoke(): JokeModel
}
