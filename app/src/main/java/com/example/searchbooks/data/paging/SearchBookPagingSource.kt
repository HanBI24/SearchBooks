package com.example.searchbooks.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.searchbooks.common.Constants.ITEM_PER_PAGE
import com.example.searchbooks.common.Constants.PREV_NEXT_ITEM_PAGE
import com.example.searchbooks.data.mapper.Mapper.toSearchBookItem
import com.example.searchbooks.data.remote.api.SearchBookApi
import com.example.searchbooks.domain.model.remote.model.SearchBookRemoteItem
import javax.inject.Inject

class SearchBookPagingSource @Inject constructor(
    private val searchBookApi: SearchBookApi,
    private val title: String
) : PagingSource<Int, SearchBookRemoteItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchBookRemoteItem> {
        return try {
            val page = params.key ?: 1
            val searchBookResponse = searchBookApi
                .getSearchBook(title, ITEM_PER_PAGE, page)
                .items.map {
                    it.toSearchBookItem()
                }

            LoadResult.Page(
                data = searchBookResponse,
                prevKey = if (page == 1) null else page.minus(PREV_NEXT_ITEM_PAGE),
                nextKey = page.plus(PREV_NEXT_ITEM_PAGE)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchBookRemoteItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}