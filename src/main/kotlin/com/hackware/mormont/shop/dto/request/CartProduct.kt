package com.hackware.mormont.shop.dto.request

import com.hackware.mormont.shop.model.product.Product
import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CartProduct(
        @field:NotBlank
        val productId: String,
        @field:Range(min=1, max = 100)
        @field:NotNull
        val quantity: Int
)