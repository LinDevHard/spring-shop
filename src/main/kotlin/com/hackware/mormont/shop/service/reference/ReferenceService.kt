package com.hackware.mormont.shop.service.reference

import com.hackware.mormont.shop.dto.product.BrandDto
import com.hackware.mormont.shop.dto.product.CategoryDto
import com.hackware.mormont.shop.dto.product.CurrencyDto

interface ReferenceService {

    fun getReferenceCurrencyList(): List<CurrencyDto>

    fun getReferenceBrandList(): List<BrandDto>

    fun getReferenceCategoryList(): List<CategoryDto>
}