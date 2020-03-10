package com.hackware.mormont.shop.repository

import com.hackware.mormont.shop.model.currency.Currency
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyRepository : JpaRepository<Currency, Long>