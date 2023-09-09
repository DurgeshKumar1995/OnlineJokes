package com.online.jokes.data.network

import com.online.jokes.data.model.JokeModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    @GET("api")
    suspend fun getJoke(
        @Query("format") format: String = "json",
    ): JokeModel
}
