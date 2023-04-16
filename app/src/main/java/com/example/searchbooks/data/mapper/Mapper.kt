package com.example.searchbooks.data.mapper

import com.example.searchbooks.data.local.entity.SearchBookEntity
import com.example.searchbooks.data.remote.dto.Item
import com.example.searchbooks.domain.repository.local.model.SearchBookLocal
import com.example.searchbooks.domain.repository.remote.model.SearchBookRemoteItem

object Mapper {
    fun Item.toSearchBookItem(): SearchBookRemoteItem {
        return SearchBookRemoteItem(
            author = author,
            discount = discount,
            image = image,
            link = link,
            publisher = publisher,
            title = title
        )
    }

    fun SearchBookEntity.toSearchBookLocal(): SearchBookLocal {
        return SearchBookLocal(searchWord = searchWord)
    }

    fun SearchBookLocal.toSearchBookEntity(searchWord: String): SearchBookEntity {
        return SearchBookEntity(searchWord = searchWord)
    }
}