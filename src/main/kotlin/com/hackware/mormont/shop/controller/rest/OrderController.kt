package com.hackware.mormont.shop.controller.rest

import com.hackware.mormont.shop.dto.order.OrderDto
import com.hackware.mormont.shop.dto.request.PagingDto
import com.hackware.mormont.shop.dto.response.Response
import com.hackware.mormont.shop.service.order.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1")
class OrderController {
    @Autowired
    private lateinit var orderService: OrderService

    @GetMapping("/order")
    fun orderList(@RequestParam(name = "page", required = false, defaultValue = "1") page: Int,
                  @RequestParam(name = "limit", required = false, defaultValue = "15") limit: Int,
                  @RequestParam(name = "sort", required = false) sort: List<String>?, authentication: Authentication): Response<List<OrderDto>> {
        return if (authentication.authorities.contains(SimpleGrantedAuthority("USER")))
            with(Response.ok<List<OrderDto>>()) {
                this.data = listOf(orderService.getOrderByUser(authentication.name))
                return@with this
            }
        else
            with(Response.ok<List<OrderDto>>()) {
                val result = orderService.getOrderList(
                        PagingDto(
                                page,
                                limit,
                                sort ?: emptyList()
                        )
                )

                this.data = result.list
                this.metadata = result.pageInfo
                return@with this
            }
    }


    @PostMapping("/order/cancel/{oid}")
    fun cancelOrder(@PathVariable oid: String, authentication: Authentication) =
            with(Response.ok<OrderDto>()) {
                this.data = orderService.cancelOrder(orderId = oid.toLong())
                return@with this
    }
}