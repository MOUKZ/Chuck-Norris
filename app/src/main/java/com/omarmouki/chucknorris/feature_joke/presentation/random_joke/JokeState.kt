package com.omarmouki.chucknorris.feature_joke.presentation.random_joke

import com.omarmouki.chucknorris.feature_joke.domin.model.JokeModel

data class JokeState(
    val isLoading: Boolean = false,
    val joke: JokeModel? = null,
    val error: String = ""
)
