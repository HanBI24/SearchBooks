package com.example.searchbooks.presentation.view.search_recent_screen.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.searchbooks.domain.repository.local.model.SearchBookLocal
import com.example.searchbooks.presentation.view.navigation.NavigationScreen
import com.example.searchbooks.presentation.view.search_recent_screen.viewmodel.SearchRecentWordViewModel

@Composable
fun SearchRecentWordScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val mainViewModel: SearchRecentWordViewModel = hiltViewModel()
    val searchWordList = mainViewModel.getAllSearchWord().collectAsState(initial = listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "최근 검색") },

                )
        },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(searchWordList.value) { searchWord ->
                    SearchWordItem(searchWord, navController)
                }
            }
        }
    )
}

@Composable
fun SearchWordItem(
    searchWord: SearchBookLocal,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(12.dp)
            .clickable {
                navController.navigate(
                    route = NavigationScreen.Main.passIsPassed(searchWord.searchWord, true)
                ) {
                    popUpTo(
                        route = NavigationScreen.Main.route
                    ) {
                        inclusive = true
                    }
                }
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(searchWord.searchWord)
    }
}