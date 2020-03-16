package com.hackware.mormont.shop.dto.order

data class OrderItemDto(
        val productId: Long,
        val name: String,
        val price: Double,
        val quantity: Int
)