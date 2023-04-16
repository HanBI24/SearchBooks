package com.example.searchbooks.data.mapper

import com.example.searchbooks.data.remote.dto.Item
import com.example.searchbooks.domain.model.remote.SearchBookRemoteItem


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
}