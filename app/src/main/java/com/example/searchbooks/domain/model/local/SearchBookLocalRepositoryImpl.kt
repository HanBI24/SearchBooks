package com.example.searchbooks.domain.model.local

import com.example.searchbooks.data.local.SearchBookDao
import com.example.searchbooks.data.mapper.Mapper.toSearchBookEntity
import com.example.searchbooks.data.mapper.Mapper.toSearchBookLocal
import com.example.searchbooks.domain.model.local.model.SearchBookLocal
import com.example.searchbooks.domain.model.local.repository.SearchBookLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchBookLocalRepositoryImpl @Inject constructor(
    private val searchBookDao: SearchBookDao
) : SearchBookLocalRepository {

    override fun getAllSearchWord(): Flow<List<SearchBookLocal>> {
        return searchBookDao.getSearchWord().map { list ->
            list.map { it.toSearchBookLocal() }
        }
    }

    override suspend fun insertSearchWord(searchWord: SearchBookLocal) {
        searchBookDao.insertSearchWord(searchWord = searchWord.toSearchBookEntity(searchWord.searchWord))
    }
}