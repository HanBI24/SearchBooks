package com.example.searchbooks.data.local.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchBookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchWord(searchWord: SearchBookEntity)

    @Query("SELECT DISTINCT searchWord FROM searchbookentity ORDER BY id DESC LIMIT 10")
    fun getSearchWord(): Flow<List<SearchBookEntity>>
}