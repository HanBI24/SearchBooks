package com.example.searchbooks.presentation.view.search_recent_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.example.searchbooks.domain.model.local.repository.SearchBookLocalRepository
import javax.inject.Inject

class SearchRecentWordViewModel @Inject constructor(
    private val searchBookLocalRepository: SearchBookLocalRepository
) : ViewModel() {
    fun getAllSearchWord() = searchBookLocalRepository.getAllSearchWord()
}