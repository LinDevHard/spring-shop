package com.hackware.mormont.shop.model.orden

import com.fasterxml.jackson.annotation.JsonFormat
import com.hackware.mormont.shop.dto.order.OrderDto
import com.hackware.mormont.shop.model.cart.Cart
import com.hackware.mormont.shop.model.user.User
import org.hibernate.annotations.ColumnDefault
import org.springframework.data.annotation.CreatedDate
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.persistence.*


@Entity
@Table(name= "orders")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val oid: Long = 0L,

        @CreatedDate
        var createdDate: LocalDateTime = LocalDateTime.now(),

        @ColumnDefault("0")
        var status: Int,

        @JoinColumn(name = "uid")
        @OneToOne
        var user: User,

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, mappedBy = "order")
        var orderItems: MutableCollection<OrderItem> = mutableListOf(),

        var orderAmount: BigDecimal
) {
        constructor(cart: Cart) : this(
                user = cart.owner!!,
                orderAmount =  cart.products.sumByDouble {it.product!!.productPrice * it.quantity  }.toBigDecimal(),
                status = OrderStatus.CREATED.code
        )
}

fun Order.mapToDTO() = OrderDto(
        orderId = this.oid,
        createdDate = this.createdDate,
        orderAmount = this.orderAmount,
        orderOwnerId = this.user.uid,
        products = this.orderItems.map { it.mapToDTO() },
        status = OrderStatus.getOrderStatusByCode(this.status).status
)
