package com.example.searchbooks.domain.repository.local

import com.example.searchbooks.domain.repository.local.model.SearchBookLocal
import kotlinx.coroutines.flow.Flow

interface SearchBookLocalRepository {
    fun getAllSearchWord(): Flow<List<SearchBookLocal>>

    suspend fun insertSearchWord(searchWord: SearchBookLocal)
}