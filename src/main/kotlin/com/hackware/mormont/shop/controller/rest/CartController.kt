package com.hackware.mormont.shop.controller.rest

import com.hackware.mormont.shop.dto.cart.CartDto
import com.hackware.mormont.shop.dto.request.CartProduct
import com.hackware.mormont.shop.dto.response.Response
import com.hackware.mormont.shop.service.cart.CartService
import com.hackware.mormont.shop.service.cart.CartServiceImpl
import com.hackware.mormont.shop.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping("api/v1")
class CartController {

    @Autowired
    private lateinit var cartService: CartService

    @GetMapping("/cart")
    fun getUserCart(principal: Principal) = with(Response.ok<CartDto>()) {
        this.data = cartService.getUserCart(principal.name)
        return@with this
    }

    @PostMapping("/cart/add")
    fun addToCart(@Valid @RequestBody cartProduct: CartProduct, principal: Principal) =
            with(Response.ok<Nothing>()) {
                cartService.addProductToCart(cartProduct, principal.name)
                return@with this
            }

    @PutMapping("/cart/{pid}")
    fun addOneToCart(@PathVariable pid: String, principal: Principal) =
            with(Response.ok<Nothing>()) {
                cartService.addProductToCart(pid, principal.name)
                return@with this
            }

    @DeleteMapping("/cart/{pid}")
    fun removeOneFromCart(@PathVariable pid: String, principal: Principal) =
            with(Response.ok<Nothing>()) {
                cartService.removeProductFromCart(pid, principal.name)
                return@with this
            }

    @PostMapping("/cart/checkout")
    fun cartCheckout(principal: Principal) =
            with(Response.ok<Nothing>()) {
                cartService.checkout(principal.name)
                return@with this
            }
}