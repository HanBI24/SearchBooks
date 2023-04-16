package com.example.searchbooks.presentation.view.search_recent_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.example.searchbooks.domain.repository.local.SearchBookLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchRecentWordViewModel @Inject constructor(
    private val searchBookLocalRepository: SearchBookLocalRepository
) : ViewModel() {
    fun getAllSearchWord() = searchBookLocalRepository.getAllSearchWord()
}