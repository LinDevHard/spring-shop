package com.hackware.mormont.shop.controller.rest

import com.hackware.mormont.shop.dto.product.ProductDetailDto
import com.hackware.mormont.shop.dto.product.ProductDto
import com.hackware.mormont.shop.dto.request.NewProductRequest
import com.hackware.mormont.shop.dto.request.PagingDto
import com.hackware.mormont.shop.dto.response.Response
import com.hackware.mormont.shop.service.product.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1")
class ProductController {

    @Autowired
    private lateinit var productService: ProductService

    @GetMapping("/product")
    fun getProductList(@RequestParam(name = "page", required = false, defaultValue = "1") page: Int,
                       @RequestParam(name = "limit", required = false, defaultValue = "15") limit: Int,
                       @RequestParam(name = "sort", required = false) sort: List<String>?) = with(Response.ok<List<ProductDto>>()) {
        val result = productService.getAllProduct(
                PagingDto(
                        page,
                        limit,
                        sort ?: emptyList()
                )
        )

        this.data = result.list
        this.metadata = result.pageInfo
        return@with this
    }

    @GetMapping("/product/{id}")
    fun getProductDetail(@PathVariable id: Long) = with(Response.ok<ProductDetailDto>()) {
        this.data = productService.getProductDetail(id)
        return@with this
    }

    @PostMapping("/product")
    fun addProduct(@Valid @RequestBody product: NewProductRequest) = with(Response.ok<Nothing>()) {
        productService.addNewProduct(product)
        return@with this
    }

}