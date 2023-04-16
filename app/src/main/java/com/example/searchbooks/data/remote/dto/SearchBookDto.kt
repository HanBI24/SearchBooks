package com.example.searchbooks.data.remote.dto

data class SearchBookDto(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)