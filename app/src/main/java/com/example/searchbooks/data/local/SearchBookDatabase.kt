package com.example.searchbooks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.searchbooks.data.local.entity.SearchBookEntity

@Database(
    entities = [SearchBookEntity::class],
    version = 1
)
abstract class SearchBookDatabase : RoomDatabase() {
    abstract val searchBookDao: SearchBookDao
}