package com.hackware.mormont.shop.dto.cart

import com.hackware.mormont.shop.dto.order.OrderItemDto
import java.time.LocalDateTime

data class CartDto (
        val totalItems: Int,
        val lastModifying: LocalDateTime,
        val products: List<OrderItemDto>
)