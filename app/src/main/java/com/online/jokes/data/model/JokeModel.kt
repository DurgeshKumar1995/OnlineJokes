package com.online.jokes.data.model

data class JokeModel(
    val joke: String,
    val time: Long = System.currentTimeMillis(),
)
