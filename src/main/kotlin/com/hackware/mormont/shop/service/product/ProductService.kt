package com.hackware.mormont.shop.service.product

import com.hackware.mormont.shop.dto.product.ProductDetailDto
import com.hackware.mormont.shop.dto.product.ProductDto
import com.hackware.mormont.shop.dto.request.NewProductRequest
import com.hackware.mormont.shop.dto.request.PagingDto
import com.hackware.mormont.shop.dto.response.PagingList
import com.hackware.mormont.shop.model.product.Product

interface ProductService {

    fun getAllProduct(pagingDto: PagingDto): PagingList<ProductDto>

    fun addNewProduct(productDto: NewProductRequest)

    fun getProductDetail(productId: Long): ProductDetailDto

    fun findProductById(productId: Long): Product
}