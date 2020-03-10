package com.hackware.mormont.shop.model.currency

import com.hackware.mormont.shop.dto.product.CurrencyDto
import javax.persistence.*

@Entity
@Table(name = "currency")
class Currency(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0L,

        val name: String = ""

)

fun Currency.mapToCurrencyDto() = CurrencyDto(
        id = this.id,
        name = this.name
)