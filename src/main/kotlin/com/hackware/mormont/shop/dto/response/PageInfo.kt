package com.hackware.mormont.shop.dto.response

data class PageInfo(
        var total: Long = 0,
        var limit: Int = 0,
        var nextPage: Int = 0,
        var hasNextPage: Boolean = false
)