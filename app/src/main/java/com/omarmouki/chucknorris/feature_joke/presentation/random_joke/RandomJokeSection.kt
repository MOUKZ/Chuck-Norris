package com.omarmouki.chucknorris.feature_joke.presentation.random_joke

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@ExperimentalAnimationApi
@Composable
fun RandomJokeSection(
    getRandomJoke: () -> Unit,
    state: State<JokeState>
) {
    Column(
        Modifier

            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.defaultMinSize(minHeight = 100.dp)) {
            androidx.compose.animation.AnimatedVisibility(visible = state.value.isLoading) {
                CircularProgressIndicator()


            }
            androidx.compose.animation.AnimatedVisibility(visible = !state.value.isLoading) {
                Text(text = state.value.joke?.content ?: "")
            }

        }
        Box() {
            Button(modifier = Modifier
                .padding(top = 20.dp),
                onClick = { getRandomJoke() }) {
                Text(text = "ClickMe")

            }

        }


    }
}