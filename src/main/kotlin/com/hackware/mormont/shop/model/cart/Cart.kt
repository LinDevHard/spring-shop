package com.hackware.mormont.shop.model.cart

import com.hackware.mormont.shop.dto.cart.CartDto
import com.hackware.mormont.shop.model.orden.OrderItem
import com.hackware.mormont.shop.model.orden.mapToDTO
import com.hackware.mormont.shop.model.product.Product
import com.hackware.mormont.shop.model.user.User
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Cart(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val cid: Long = 0L,

        @LastModifiedDate
        var lastModified: LocalDateTime = LocalDateTime.now(),

        @OneToMany(mappedBy = "cart",cascade = [CascadeType.ALL], orphanRemoval = true)
        var products: MutableCollection<OrderItem> = mutableListOf(),

        var quantity: Int = products.size,

        @OneToOne(fetch = FetchType.LAZY)
        @MapsId
        var owner: User? = null
)

fun Cart.mapToDTO() = CartDto(
        totalItems = this.quantity,
        lastModifying = this.lastModified,
        products = this.products.map { it.mapToDTO() }
)