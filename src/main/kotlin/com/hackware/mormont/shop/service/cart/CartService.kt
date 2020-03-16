package com.hackware.mormont.shop.service.cart

import com.hackware.mormont.shop.dto.cart.CartDto
import com.hackware.mormont.shop.dto.request.CartProduct
import com.hackware.mormont.shop.dto.user.UserDto

interface CartService {

    fun getUserCart(userName: String): CartDto

    fun addProductToCart(productId: String, userName: String)

    fun addProductToCart(productRequest: CartProduct, userName: String)

    fun removeProductFromCart(productId: String, userName: String)

    fun checkout(userName: String)
}