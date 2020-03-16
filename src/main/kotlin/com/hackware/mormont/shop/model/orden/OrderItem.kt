package com.hackware.mormont.shop.model.orden

import com.hackware.mormont.shop.dto.order.OrderItemDto
import com.hackware.mormont.shop.model.cart.Cart
import com.hackware.mormont.shop.model.product.Product
import javax.persistence.*


@Entity
data class OrderItem(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val itemId: Long = 0L,

        var quantity: Int = 0,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "pid")
        val product: Product? = null,

        @JoinColumn(name  = "oid")
        @ManyToOne(cascade = [CascadeType.ALL], targetEntity = Order::class)
        var order: Order? = null,

        @JoinColumn(name = "cid")
        @ManyToOne(cascade = [CascadeType.ALL], targetEntity = Cart::class)
        var cart: Cart? = null
){

        override fun toString(): String {
                return "id=$itemId, " +
                        "quantity=$quantity," +
                        "product=$product"
        }
}

fun OrderItem.mapToDTO() = OrderItemDto(
        productId = this.product!!.id,
        name = this.product.name,
        price = this.product.productPrice,
        quantity = this.quantity
)