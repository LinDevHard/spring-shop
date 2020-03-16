package com.hackware.mormont.shop.repository

import com.hackware.mormont.shop.model.orden.Order
import com.hackware.mormont.shop.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun findByUser(user: User): Order

}