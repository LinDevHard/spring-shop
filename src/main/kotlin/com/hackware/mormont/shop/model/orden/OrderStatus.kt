package com.hackware.mormont.shop.model.orden

import com.hackware.mormont.shop.util.CodeEnum

enum class OrderStatus (val code: Int,val status: String) {
    CREATED(0,"created"),
    PROCESSING(1, "process"),
    FINISHED(2, "finish"),
    CANCELED(3, "canceled"),
    ERROR(10, "error");

    companion object {
        fun getOrderStatusByCode(code: Int): OrderStatus {
            for (oStatus in OrderStatus.values()){
                if(oStatus.code == code)
                    return oStatus
            }
            return ERROR
        }
    }
}