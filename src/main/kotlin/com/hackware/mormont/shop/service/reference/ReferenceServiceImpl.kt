package com.hackware.mormont.shop.service.reference

import com.hackware.mormont.shop.dto.product.BrandDto
import com.hackware.mormont.shop.dto.product.CategoryDto
import com.hackware.mormont.shop.dto.product.CurrencyDto
import com.hackware.mormont.shop.model.currency.mapToCurrencyDto
import com.hackware.mormont.shop.model.product.mapToBrandDto
import com.hackware.mormont.shop.model.product.mapToCategoryDto
import com.hackware.mormont.shop.repository.BrandRepository
import com.hackware.mormont.shop.repository.CategoryRepository
import com.hackware.mormont.shop.repository.CurrencyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReferenceServiceImpl : ReferenceService {
    @Autowired
    private lateinit var currencyRepository: CurrencyRepository

    @Autowired
    private lateinit var brandRepository: BrandRepository

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    override fun getReferenceCurrencyList(): List<CurrencyDto> {
        val currencyList = currencyRepository.findAll()
        return currencyList.map { currency ->
            currency.mapToCurrencyDto()
        }
    }

    override fun getReferenceBrandList(): List<BrandDto> {
        val brandList = brandRepository.findAll()
        return brandList.map { brand ->
            brand.mapToBrandDto()
        }
    }

    override fun getReferenceCategoryList(): List<CategoryDto> {
        val categoryList = categoryRepository.findAll()
        return categoryList.map { category ->
            category.mapToCategoryDto()
        }
    }

}