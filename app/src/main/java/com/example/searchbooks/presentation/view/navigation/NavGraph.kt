package com.example.searchbooks.presentation.view.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.searchbooks.presentation.view.search_recent_screen.view.SearchRecentWordScreen
import com.example.searchbooks.presentation.view.search_screen.view.MainScreen

@ExperimentalCoilApi
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.Main.route,
    ) {
        composable(
            route = NavigationScreen.Main.route,
            arguments = listOf(navArgument(DEFAULT_ARGUMENT_KEY) {
                type = NavType.StringType
            },
                navArgument(DEFAULT_ARGUMENT_KEY2) {
                    type = NavType.BoolType
                }
            )
        ) {
            MainScreen(
                navController,
                it.arguments?.getString(DEFAULT_ARGUMENT_KEY) ?: "",
                it.arguments?.getBoolean(DEFAULT_ARGUMENT_KEY2) ?: false
            )
        }
        composable(
            route = NavigationScreen.RecentSearchWord.route,
        ) {
            SearchRecentWordScreen(
                modifier = Modifier
                    .fillMaxSize(),
                navController = navController
            )
        }
    }
}