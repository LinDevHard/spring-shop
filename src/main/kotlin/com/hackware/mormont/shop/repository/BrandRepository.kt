package com.hackware.mormont.shop.repository

import com.hackware.mormont.shop.model.product.Brand
import org.springframework.data.jpa.repository.JpaRepository

interface BrandRepository : JpaRepository<Brand, Long>