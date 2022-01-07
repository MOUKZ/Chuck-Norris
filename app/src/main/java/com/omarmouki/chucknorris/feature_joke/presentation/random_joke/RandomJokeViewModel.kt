package com.omarmouki.chucknorris.feature_joke.presentation.random_joke

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarmouki.chucknorris.core.utils.CommonResponse
import com.omarmouki.chucknorris.feature_joke.domin.use_case.GetRandomJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RandomJokeViewModel @Inject constructor(
    private val getRandomJokeUseCase: GetRandomJokeUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf<JokeState>(JokeState())
    val state: State<JokeState> = _state
init {
    getRandomJoke()
}

    fun getRandomJoke() {
        getRandomJokeUseCase.invoke().onEach { event ->
            when (event) {
                is CommonResponse.Success -> {
                    _state.value = JokeState(isLoading = false, joke = event.data, error = "")

                }
                is CommonResponse.Loading -> {
                    _state.value = JokeState(isLoading = true, joke = event.data, error = "")
                }
                is CommonResponse.Error -> {
                    _state.value =
                        JokeState(
                            isLoading = false,
                            joke = event.data,
                            error = event.message ?: "error"
                        )

                }
            }
        }.launchIn(viewModelScope)
    }
}