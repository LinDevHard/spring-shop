package com.hackware.mormont.shop.service.auth

import com.hackware.mormont.shop.dto.response.AuthSuccessResponse
import com.hackware.mormont.shop.dto.user.UserDto

interface AuthService {

    fun authenticateUser(user: UserDto): AuthSuccessResponse

}