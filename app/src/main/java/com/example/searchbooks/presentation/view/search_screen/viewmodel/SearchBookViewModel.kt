package com.example.searchbooks.presentation.view.search_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.searchbooks.common.Constants.ITEM_PER_PAGE
import com.example.searchbooks.data.paging.SearchBookPagingSource
import com.example.searchbooks.data.remote.api.SearchBookApi
import com.example.searchbooks.domain.repository.local.model.SearchBookLocal
import com.example.searchbooks.domain.repository.local.SearchBookLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchBookViewModel @Inject constructor(
    private val searchBookApi: SearchBookApi,
    private val searchBookLocalRepository: SearchBookLocalRepository
) : ViewModel(){

    fun getSearchBookPagination(title: String) =
        Pager(PagingConfig(pageSize = ITEM_PER_PAGE)) {
            SearchBookPagingSource(searchBookApi, title)
        }.flow.cachedIn(viewModelScope)

    suspend fun insertSearchWord(searchWord: String) {
        viewModelScope.launch {
            searchBookLocalRepository.insertSearchWord(
                SearchBookLocal(searchWord = searchWord)
            )
        }
    }
}