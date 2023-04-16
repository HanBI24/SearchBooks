package com.example.searchbooks.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchBookApi {

    @Headers(
        "X-Naver-Client-Id: B1txQ9y2VOVRWeFFDWQI",
        "X-Naver-Client-Secret: ptujwOMNVL"
    )
    @GET("/v1/search/book.json")
    suspend fun getSearchBook(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int
    ): SearchBookDto
}