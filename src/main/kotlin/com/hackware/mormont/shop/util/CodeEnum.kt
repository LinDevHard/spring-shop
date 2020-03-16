package com.hackware.mormont.shop.util

interface CodeEnum {
    fun getByCode(code: Int): CodeEnum
}