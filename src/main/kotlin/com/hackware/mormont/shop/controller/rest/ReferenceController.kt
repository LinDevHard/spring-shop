package com.hackware.mormont.shop.controller.rest

import com.hackware.mormont.shop.dto.product.BrandDto
import com.hackware.mormont.shop.dto.product.CategoryDto
import com.hackware.mormont.shop.dto.product.CurrencyDto
import com.hackware.mormont.shop.dto.response.Response
import com.hackware.mormont.shop.service.reference.ReferenceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/reference")
class ReferenceController {

    @Autowired
    private lateinit var referenceService: ReferenceService


    @GetMapping("/currency")
    fun getCurrency() = with(Response.ok<List<CurrencyDto>>()) {
        this.data = referenceService.getReferenceCurrencyList()
        return@with this
    }

    @GetMapping("/brand")
    fun getBrand() = with(Response.ok<List<BrandDto>>()) {
        this.data = referenceService.getReferenceBrandList()
        return@with this
    }

    @GetMapping("/category")
    fun getCategory() = with(Response.ok<List<CategoryDto>>()) {
        this.data = referenceService.getReferenceCategoryList()
        return@with this
    }
}