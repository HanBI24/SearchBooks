package com.example.searchbooks.data.remote.dto

import com.example.searchbooks.data.remote.dto.Item

data class SearchBookDto(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)