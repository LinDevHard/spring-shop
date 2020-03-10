package com.hackware.mormont.shop.dto.product

data class ProductDto(
        var id: Long = 0L,
        val name: String = "",
        val description: String = "",
        val productFeatures: List<String>,
        val productPrice: Double = .0,
        val currency: CurrencyDto? = null,
        val productBrand: BrandDto? = null,
        val productModel: String = "",
        val freeShipping: Boolean = false
)