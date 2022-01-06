package com.omarmouki.chucknorris.feature_joke.data.remote

import com.omarmouki.chucknorris.feature_joke.data.remote.dto.JokeDto
import retrofit2.http.GET

interface ChuckNorrisApi {
    @GET("/jokes/random")
    suspend fun getRandomJoke():JokeDto


    companion object {
        val BASE_URL = "https://api.chucknorris.io"
    }
}