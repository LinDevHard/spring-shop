package com.hackware.mormont.shop.repository

import com.hackware.mormont.shop.model.cart.Cart
import com.hackware.mormont.shop.model.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<Cart, Long> {
    fun findByOwner(owner: User) : Cart

}