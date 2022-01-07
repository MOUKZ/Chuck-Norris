package com.omarmouki.chucknorris

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.*
import com.omarmouki.chucknorris.feature_joke.presentation.random_joke.JokeState
import com.omarmouki.chucknorris.feature_joke.presentation.random_joke.RandomJokeSection
import com.omarmouki.chucknorris.feature_joke.presentation.random_joke.RandomJokeViewModel
import com.omarmouki.chucknorris.ui.theme.ChuckNorrisTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChuckNorrisTheme {
//                val viewModel: RandomJokeViewModel = hiltViewModel()
//                val state = viewModel.state
//                tabs()

                Scaffold() {
                    val viewModel: RandomJokeViewModel = hiltViewModel()
                    val state = viewModel.state
                    tabsWithSwiping()


                }
            }
        }
    }
}


@ExperimentalPagerApi
@Composable
fun tabs() {
    var tabIndex by remember { mutableStateOf(0) } // 1.

    val tabTitles = listOf("Hello", "There", "World")
    Column { // 2.
        TabRow(selectedTabIndex = tabIndex) { // 3.
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index, // 4.
                    onClick = { tabIndex = index },
                    text = { Text(text = title) }) // 5.
            }
        }
        when (tabIndex) { // 6.
            0 -> Text("Hello content")
            1 -> Text("There content")
            2 -> Text("World content")
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi // 1.
@Preview
@Composable
fun tabsWithSwiping(
    viewModel: RandomJokeViewModel = hiltViewModel()
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("random", "There", "World")
    val pagerState = rememberPagerState() // 2.
    Column {
        TabRow(selectedTabIndex = tabIndex,
            indicator = { tabPositions -> // 3.
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(
                        pagerState,
                        tabPositions
                    )
                )
            }) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = title) })
            }
        }
        HorizontalPager(
            // 4.
            count = tabTitles.size,
            state = pagerState,
        ) { tabIndex ->
            if(tabIndex ==0){
                RandomJokeSection(getRandomJoke = { viewModel.getRandomJoke() }, state = viewModel.state)
            }else

            Text(
                tabIndex.toString(),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
        }
    }
}