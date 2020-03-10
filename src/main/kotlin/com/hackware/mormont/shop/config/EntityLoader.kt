package com.hackware.mormont.shop.config

import com.hackware.mormont.shop.model.currency.Currency
import com.hackware.mormont.shop.model.product.Brand
import com.hackware.mormont.shop.repository.BrandRepository
import com.hackware.mormont.shop.repository.CurrencyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class EntityLoader {
    @Autowired
    private lateinit var currencyRepository: CurrencyRepository

    @Autowired
    private lateinit var brandRepository: BrandRepository

    @PostConstruct
    fun initApiUserData() {
        initCurrency()
        initBrand()
    }

    private fun initBrand() {
        val unknown = Brand(name = "Unknown")

        brandRepository.saveAll(listOf(unknown))
    }

    private fun initCurrency() {
        val usd = Currency(name = "USD")
        val eur = Currency(name = "EUR")
        val rub = Currency(name = "RUB")

        currencyRepository.saveAll(listOf(usd, eur, rub))
    }
}