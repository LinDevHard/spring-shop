package com.hackware.mormont.shop.model.product

import com.hackware.mormont.shop.dto.product.ProductDetailDto
import com.hackware.mormont.shop.dto.product.ProductDto
import com.hackware.mormont.shop.model.currency.Currency
import com.hackware.mormont.shop.model.currency.mapToCurrencyDto
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(
        val name: String = "",
        val description: String = "",
        val productFeatures: String,
        val productPrice: Double = .0,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "currency_id")
        val currency: Currency? = null,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "brand_id")
        val productBrand: Brand? = null,
        val productModel: String = "",
        val freeShipping: Boolean = false,
        var productPriceRate: Int = 0,
        var productQualityRate: Int = 0,
        var deliveryTimeRate: Int = 0,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0L
)

fun Product.mapToProductDto() = ProductDto(
        this.id,
        this.name,
        this.description,
        this.productFeatures.split("\n"),
        this.productPrice,
        this.currency?.mapToCurrencyDto(),
        this.productBrand?.mapToBrandDto(),
        this.productModel,
        this.freeShipping
)

fun Product.mapToProductDetailDto() = ProductDetailDto(
        this.id,
        this.name,
        this.description,
        this.productFeatures.split("\n"),
        this.productPrice,
        this.currency?.mapToCurrencyDto(),
        this.productBrand?.mapToBrandDto(),
        this.productModel,
        this.freeShipping,
        this.productPriceRate,
        this.productQualityRate,
        this.deliveryTimeRate
)