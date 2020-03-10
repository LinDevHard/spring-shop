package com.hackware.mormont.shop.dto.response

data class PagingList<out T>(
        val list: List<T> = emptyList(),
        val pageInfo: PageInfo = PageInfo()
)