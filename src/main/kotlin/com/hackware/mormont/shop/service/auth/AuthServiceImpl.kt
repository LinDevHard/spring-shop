package com.hackware.mormont.shop.service.auth

import com.hackware.mormont.shop.dto.response.AuthSuccessResponse
import com.hackware.mormont.shop.dto.user.UserDto
import com.hackware.mormont.shop.exception.EntityType
import com.hackware.mormont.shop.exception.ExceptionType
import com.hackware.mormont.shop.security.CustomUserDetailsService
import com.hackware.mormont.shop.service.user.UserService
import com.hackware.mormont.shop.util.JavaWebTokenUtil
import com.hackware.mormont.shop.util.exception
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl : AuthService {
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtTokenUtil: JavaWebTokenUtil

    @Autowired
    private lateinit var userDetailsService: CustomUserDetailsService

    override fun authenticateUser(user: UserDto): AuthSuccessResponse {
        val userModel = userService.findUserByEmail(user.email)
                ?: throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, "User not found!")

        authenticate(user.email, user.password)

        val userDetails = userDetailsService.loadUserByUsername(user.email)
        val token = jwtTokenUtil.generateToken(userDetails)

        return AuthSuccessResponse(token)
    }


    private fun authenticate(username: String, password: String?) {
        try {
            println(username)
            println(password)
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }
}