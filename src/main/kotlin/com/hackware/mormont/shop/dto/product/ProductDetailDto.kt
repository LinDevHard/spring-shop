package com.hackware.mormont.shop.dto.product

data class ProductDetailDto(
        var id: Long = 0L,
        val name: String = "",
        val description: String = "",
        val productFeatures: List<String>,
        val productPrice: Double = .0,
        val currency: CurrencyDto? = null,
        val productBrand: BrandDto? = null,
        val productModel: String = "",
        val freeShipping: Boolean = false,
        var productPriceRate: Int = 0,
        var productQualityRate: Int = 0,
        var deliveryTimeRate: Int = 0
)