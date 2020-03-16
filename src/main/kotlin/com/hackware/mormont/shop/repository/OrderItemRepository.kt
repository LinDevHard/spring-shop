package com.hackware.mormont.shop.repository

import com.hackware.mormont.shop.model.orden.OrderItem
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository : JpaRepository<OrderItem, Long>