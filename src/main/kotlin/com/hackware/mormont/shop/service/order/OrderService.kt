package com.hackware.mormont.shop.service.order

import com.hackware.mormont.shop.dto.order.OrderDto
import com.hackware.mormont.shop.dto.request.PagingDto
import com.hackware.mormont.shop.dto.response.PagingList
import javax.print.attribute.standard.JobOriginatingUserName

interface OrderService {

    fun getOrderList(paging: PagingDto): PagingList<OrderDto>

    fun getOrderByUser(userName: String): OrderDto

    fun cancelOrder(orderId: Long) : OrderDto
}