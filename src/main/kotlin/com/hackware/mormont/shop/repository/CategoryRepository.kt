package com.hackware.mormont.shop.repository

import com.hackware.mormont.shop.model.product.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long>