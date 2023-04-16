package com.example.searchbooks.presentation.view.navigation

const val DEFAULT_ARGUMENT_KEY = "word"
const val DEFAULT_ARGUMENT_KEY2 = "isPassed"

sealed class NavigationScreen(val route: String) {
    object Main: NavigationScreen(route = "main/{$DEFAULT_ARGUMENT_KEY}/{$DEFAULT_ARGUMENT_KEY2}") {
        fun passIsPassed(word: String, isPassed: Boolean): String {
            return "main/$word/$isPassed"
        }
    }
    object RecentSearchWord: NavigationScreen(route = "recent_search_word")
}