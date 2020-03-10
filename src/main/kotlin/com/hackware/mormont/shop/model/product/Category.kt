package com.hackware.mormont.shop.model.product

import com.hackware.mormont.shop.dto.product.CategoryDto
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "category")
data class Category(
        @Id
        var id: Long = 0L,
        var name: String = ""
)

fun Category.mapToCategoryDto() = CategoryDto(
        id = this.id,
        name = this.name
)