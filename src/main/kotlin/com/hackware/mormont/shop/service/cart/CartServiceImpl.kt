package com.hackware.mormont.shop.service.cart

import com.hackware.mormont.shop.dto.cart.CartDto
import com.hackware.mormont.shop.dto.request.CartProduct
import com.hackware.mormont.shop.exception.EntityType
import com.hackware.mormont.shop.exception.ExceptionType
import com.hackware.mormont.shop.model.cart.Cart
import com.hackware.mormont.shop.model.cart.mapToDTO
import com.hackware.mormont.shop.model.orden.Order
import com.hackware.mormont.shop.model.orden.OrderItem
import com.hackware.mormont.shop.model.product.Product
import com.hackware.mormont.shop.repository.CartRepository
import com.hackware.mormont.shop.repository.OrderItemRepository
import com.hackware.mormont.shop.repository.OrderRepository
import com.hackware.mormont.shop.service.product.ProductService
import com.hackware.mormont.shop.service.user.UserService
import com.hackware.mormont.shop.util.exception
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception

@Service
class CartServiceImpl: CartService {
    @Autowired
    private lateinit var cartRepository: CartRepository

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var orderItemRepository: OrderItemRepository

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Transactional
    override fun getUserCart(userName: String): CartDto =
       getStockUserCart(userName).mapToDTO()

    @Transactional
    override fun addProductToCart(productId: String, userName: String) {
        val userCart = getStockUserCart(userName)
        val item = userCart.products.find { it.product?.id == productId.toLong() }
        if (item != null)
            addProductToCart(CartProduct(productId, item.quantity + 1), userName)
        else
            addProductToCart(CartProduct(productId, 1), userName)

    }

    @Transactional
    override fun addProductToCart(productRequest: CartProduct, userName: String) {
        val userCart = getStockUserCart(userName)
        val product: Product
        try {
            product = productService.findProductById(productId = productRequest.productId.toLong())
        } catch (e: Exception) {
            throw exception(EntityType.PRODUCT, ExceptionType.INVALID_ENTITY_ID, productRequest.productId)
        }

        val cartItems = userCart.products.toMutableList()
        val item = cartItems.find { it.product?.id == productRequest.productId.toLong() }
        item?.let { orderItem ->
            orderItem.quantity = productRequest.quantity
            orderItemRepository.save(orderItem)
        }
        if (item == null) {
            val newItem = OrderItem(
                    quantity = productRequest.quantity,
                    product = product,
                    cart = userCart
            )
            userCart.products.add(newItem)
            userCart.quantity = userCart.products.size
            orderItemRepository.save(newItem)
        }
        println(userCart)
        cartRepository.save(userCart)
    }

    @Transactional
    override fun removeProductFromCart(productId: String, userName: String) {
        val userCart = getStockUserCart(userName)
        try {
            val item = userCart.products.find{it.product?.id == productId.toLong()}
            item?.let {
                orderItemRepository.deleteById(it.itemId)
            }
        } catch (e: Exception) {
            throw exception(EntityType.PRODUCT, ExceptionType.INVALID_ENTITY_ID, productId)
        }
    }

    private fun getStockUserCart(userName: String): Cart {
        val user = userService.getUserByName(userName)
        return user.cart!!
    }

    @Transactional
    override fun checkout(userName: String) {
        val userCart = getStockUserCart(userName)

        val order = Order(userCart)
        orderRepository.save(order)

        userCart.products.forEach {orderItem ->
            orderItem.cart = null
            orderItem.order = order
            orderItemRepository.save(orderItem)
        }
    }
}