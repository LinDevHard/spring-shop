package com.hackware.mormont.shop.security

import com.hackware.mormont.shop.dto.user.UserDto
import com.hackware.mormont.shop.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer


@Service
class CustomUserDetailsService : UserDetailsService {

    @Autowired
    private lateinit var userService: UserService

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val userDto = userService.findUserByEmail(email)
        return if (userDto != null) {
            val authorities = getUserAuthority(userDto.userRoles)
            buildUserForAuthentication(userDto, authorities)
        } else {
            throw UsernameNotFoundException("user with email $email does not exist.")
        }
    }

    private fun getUserAuthority(userRoles: List<String>): List<GrantedAuthority> {
        val roles: MutableSet<GrantedAuthority> = HashSet()
        userRoles.forEach(Consumer { role: String -> roles.add(SimpleGrantedAuthority(role)) })
        return ArrayList(roles)
    }

    private fun buildUserForAuthentication(user: UserDto, authorities: List<GrantedAuthority>): UserDetails {
        return User(user.email, user.password, authorities)
    }
}