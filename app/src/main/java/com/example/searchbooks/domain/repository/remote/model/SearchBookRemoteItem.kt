package com.example.searchbooks.domain.repository.remote.model

data class SearchBookRemoteItem(
    val author: String,
    val discount: String,
    val image: String,
    val link: String,
    val publisher: String,
    val title: String
)