package com.hackware.mormont.shop.dto.order

import com.hackware.mormont.shop.dto.product.ProductDto
import org.springframework.data.annotation.CreatedDate
import java.math.BigDecimal
import java.time.LocalDateTime

data class OrderDto(
        val orderId: Long,
        val createdDate: LocalDateTime,
        val status: String,
        val orderOwnerId: Long,
        val orderAmount: BigDecimal,
        val products: List<OrderItemDto>
)