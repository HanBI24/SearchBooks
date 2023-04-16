package com.example.searchbooks.data.remote.api

import com.example.searchbooks.BuildConfig
import com.example.searchbooks.data.remote.dto.SearchBookDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchBookApi {

    @Headers(
        BuildConfig.Client_ID,
        BuildConfig.Client_Secret,
    )
    @GET("/v1/search/book.json")
    suspend fun getSearchBook(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int
    ): SearchBookDto
}