package com.omarmouki.chucknorris.feature_joke.domin.repository

import com.omarmouki.chucknorris.feature_joke.data.remote.dto.JokeDto

interface JokesRepository {
    suspend fun getRandomJoke(): JokeDto
}