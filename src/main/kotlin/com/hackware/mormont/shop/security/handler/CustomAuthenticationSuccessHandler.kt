package com.hackware.mormont.shop.security.handler

import com.hackware.mormont.shop.dto.user.UserRoles
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse?, authentication: Authentication) {
        response?.status = HttpServletResponse.SC_OK

        authentication.authorities.forEach {
            if (UserRoles.ADMIN.name == it.authority) {
                response?.sendRedirect("/dashboard")
            }
        }
    }
}