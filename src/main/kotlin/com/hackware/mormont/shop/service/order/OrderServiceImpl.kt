package com.hackware.mormont.shop.service.order

import com.hackware.mormont.shop.dto.order.OrderDto
import com.hackware.mormont.shop.dto.request.PagingDto
import com.hackware.mormont.shop.dto.response.PageInfo
import com.hackware.mormont.shop.dto.response.PagingList
import com.hackware.mormont.shop.exception.EntityType
import com.hackware.mormont.shop.exception.ExceptionType
import com.hackware.mormont.shop.model.orden.OrderStatus
import com.hackware.mormont.shop.model.orden.mapToDTO
import com.hackware.mormont.shop.repository.OrderRepository
import com.hackware.mormont.shop.service.user.UserService
import com.hackware.mormont.shop.util.exception
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImpl : OrderService {

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var userService: UserService

    @Transactional
    override fun getOrderList(paging: PagingDto): PagingList<OrderDto> {
        val pagingRequest = PageRequest.of(paging.page - 1, paging.limit)
        val page = orderRepository.findAll(pagingRequest)
        val pageInfo = PageInfo(
                page.totalElements,
                page.size,
                page.pageable.pageNumber + 2,
                page.hasNext()
        )
        return PagingList(
                page.content.map { order ->
                    order.mapToDTO()
                },
                pageInfo
        )
    }

    @Transactional
    override fun getOrderByUser(userName: String): OrderDto {
        val order = orderRepository.findByUser(userService.getUserByName(userName))
        return order.mapToDTO()
    }

    @Transactional
    override fun cancelOrder(orderId: Long) : OrderDto {
        val order  = orderRepository.findById(orderId)
        if (order.isEmpty)
            throw exception(EntityType.ORDER, ExceptionType.ENTITY_NOT_FOUND, orderId.toString() )

        val orderPresent = order.get()
        orderPresent.status = OrderStatus.CANCELED.code
        orderRepository.save(orderPresent)

        return orderPresent.mapToDTO()
    }


}