package com.hackware.mormont.shop.dto.request

data class PagingDto(
        val page: Int = 1,
        val limit: Int = 15,
        val sort: List<String> = emptyList()
)