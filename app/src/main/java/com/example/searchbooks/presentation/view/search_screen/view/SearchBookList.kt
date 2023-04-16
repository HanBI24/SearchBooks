package com.example.searchbooks.presentation.view.search_screen.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.searchbooks.domain.repository.remote.model.SearchBookRemoteItem
import com.example.searchbooks.presentation.view.search_screen.viewmodel.SearchBookViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@ExperimentalCoilApi
@Composable
fun SearchInputWord(recentWord: String = "", isPassed: Boolean = false) {
    val mainViewModel: SearchBookViewModel = hiltViewModel()
    var searchWord by rememberSaveable { mutableStateOf(recentWord) }
    var isClicked by remember { mutableStateOf(false) }
    var isRecentPassed by remember { mutableStateOf(isPassed) }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    if(searchWord.isNotBlank() && isRecentPassed) {
        isClicked = true
        isRecentPassed = false
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = searchWord,
                onValueChange = { textValue ->
                    isClicked = false
                    searchWord = textValue
                }
            )
            Button(
                onClick = {
                    focusManager.clearFocus()
                    isClicked = true
                }
            ) {
                Text("검색")
            }
        }

        if(isClicked) {
            val searchBook = mainViewModel.getSearchBookPagination(searchWord).collectAsLazyPagingItems()
            SearchBookList(searchBook = searchBook)
            insertSearchWord {
                scope.launch {
                    if(searchWord.isNotBlank())
                        mainViewModel.insertSearchWord(searchWord)
                }
            }
        }
    }
}

@Composable
fun insertSearchWord(onInsert: () -> Job) = onInsert()

@ExperimentalCoilApi
@Composable
fun SearchBookList(searchBook: LazyPagingItems<SearchBookRemoteItem>) {
    val scrollState = rememberLazyListState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(searchBook) { searchBookInfo ->
                if (searchBookInfo != null) {
                    SearchBookItem(searchBookInfo = searchBookInfo)
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun SearchBookItem(searchBookInfo: SearchBookRemoteItem) {
    val searchBookContext = LocalContext.current
    val painter = rememberImagePainter(data = searchBookInfo.image) {
        crossfade(1000)
    }

    Box(
        modifier = Modifier
            .clickable {
                val searchBookBrowserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(searchBookInfo.link)
                )
                ContextCompat.startActivity(searchBookContext, searchBookBrowserIntent, null)
            }
            .height(150.dp),
        contentAlignment = Alignment.Center
    ) {
        ImagePainterState(painter = painter.state)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                modifier = Modifier
                    .size(150.dp),
                painter = painter,
                contentDescription = "Search Book Image",
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "제목: ${searchBookInfo.title}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "저자: ${searchBookInfo.author}"
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "출판사: ${searchBookInfo.publisher}"
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "가격: ${searchBookInfo.discount}",
                    overflow = TextOverflow.Visible
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun ImagePainterState(painter: ImagePainter.State) {
    when (painter) {
        is ImagePainter.State.Loading -> {
            ShowProgressBar()
        }
        is ImagePainter.State.Empty -> {
            ShowProgressBar()
        }
        else -> {}
    }
}

@Composable
fun ShowProgressBar() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            color = Color.Red,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}