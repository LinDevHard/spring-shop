package com.hackware.mormont.shop.dto.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NewProductRequest(
        @field:NotNull
        @field:Size(min = 2, max = 200)
        var name: String = "",
        @field:NotNull
        var description: String = "",
        @field:NotNull
        var productFeatures: String,
        @field:NotNull
        var productPrice: Double = .0,
        @field:NotNull
        var currency: Long = 0L,
        @field:NotNull
        var productBrand: Long = 0L,
        @field:NotNull
        var productModel: String = "",
        @field:NotNull
        var freeShipping: Boolean = false
)