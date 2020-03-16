package com.hackware.mormont.shop.controller.rest

import com.hackware.mormont.shop.dto.request.UserAuthRequest
import com.hackware.mormont.shop.dto.request.mapToUSerDto
import com.hackware.mormont.shop.dto.response.AuthSuccessResponse
import com.hackware.mormont.shop.dto.response.Response
import com.hackware.mormont.shop.service.auth.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api")
class AuthController {


    @Autowired
    private lateinit var authService: AuthService

    @PostMapping("/auth")
    fun authUser(@Valid @RequestBody authRequest: UserAuthRequest) = with(Response.ok<AuthSuccessResponse>()) {
        this.data = authService.authenticateUser(authRequest.mapToUSerDto())
        return@with this
    }

}