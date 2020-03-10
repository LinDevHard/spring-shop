package com.hackware.mormont.shop.repository

import com.hackware.mormont.shop.model.product.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long>