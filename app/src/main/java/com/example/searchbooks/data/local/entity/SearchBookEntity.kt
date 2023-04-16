package com.example.searchbooks.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchBookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val searchWord: String
)