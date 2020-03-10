package com.hackware.mormont.shop.model.product

import com.hackware.mormont.shop.dto.product.BrandDto
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "brand")
data class Brand(
        @Id
        var id: Long = 0L,
        var name: String = ""
)

fun Brand.mapToBrandDto() = BrandDto(
        id = this.id,
        name = this.name
)