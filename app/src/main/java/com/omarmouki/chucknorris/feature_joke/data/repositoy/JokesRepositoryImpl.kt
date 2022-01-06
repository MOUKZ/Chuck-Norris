package com.omarmouki.chucknorris.feature_joke.data.repositoy

import com.omarmouki.chucknorris.feature_joke.data.remote.ChuckNorrisApi
import com.omarmouki.chucknorris.feature_joke.data.remote.dto.JokeDto
import com.omarmouki.chucknorris.feature_joke.domin.repository.JokesRepository
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    private val api: ChuckNorrisApi

) : JokesRepository {
    override suspend fun getRandomJoke(): JokeDto {
        return api.getRandomJoke()
    }
}