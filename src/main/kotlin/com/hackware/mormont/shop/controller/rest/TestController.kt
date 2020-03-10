package com.hackware.mormont.shop.controller.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/test")
class TestController {

    @GetMapping
    fun list(): String {
        return "test"
    }
}