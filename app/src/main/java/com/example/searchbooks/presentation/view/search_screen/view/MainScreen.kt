package com.example.searchbooks.presentation.view.search_screen.view

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.searchbooks.presentation.view.navigation.NavigationScreen

@ExperimentalCoilApi
@Composable
fun MainScreen(
    navController: NavHostController,
    recentWord: String = "",
    isPassed: Boolean = false
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "책 검색") },
                actions = {
                    TextButton(
                        onClick = {
                            navController.navigate(
                                route = NavigationScreen.RecentSearchWord.route
                            )
                        }
                    ) {
                        Text(text = "최근 검색어", color = Color.White)
                    }
                }
            )
        },
        content = {
            SearchInputWord(recentWord, isPassed)
        }
    )
}