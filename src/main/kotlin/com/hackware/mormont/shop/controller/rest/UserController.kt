package com.hackware.mormont.shop.controller.rest

import com.hackware.mormont.shop.dto.request.UserSignupRequest
import com.hackware.mormont.shop.dto.request.toUserDto
import com.hackware.mormont.shop.dto.response.Response
import com.hackware.mormont.shop.dto.user.UserDto
import com.hackware.mormont.shop.service.user.UserService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/user")
@Api(value = "User Signup", description = "Endpoint for signup user in system.")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/signup")
    fun signup(@Valid @RequestBody userSignupRequest: UserSignupRequest) = with(Response.ok<UserDto>()) {
        this.data = registerUser(userSignupRequest, false)
        return@with this
    }

    fun registerUser(userSignupRequest: UserSignupRequest, isAdmin: Boolean): UserDto {
        val userDto = userSignupRequest.toUserDto(isAdmin)
        return userService.signup(userDto)
    }

}