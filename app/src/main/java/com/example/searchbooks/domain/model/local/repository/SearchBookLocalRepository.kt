package com.example.searchbooks.domain.model.local.repository

import com.example.searchbooks.domain.model.local.model.SearchBookLocal
import kotlinx.coroutines.flow.Flow

interface SearchBookLocalRepository {
    fun getAllSearchWord(): Flow<List<SearchBookLocal>>

    suspend fun insertSearchWord(searchWord: SearchBookLocal)
}