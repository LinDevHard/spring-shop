package com.hackware.mormont.shop.dto.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.hackware.mormont.shop.dto.user.UserDto
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank


@JsonIgnoreProperties(ignoreUnknown = true)
data class UserAuthRequest(


        @field:NotBlank(message = "{constraints.NotEmpty.message}")
        @field:Email
        var email: String?,

        @field:NotBlank
        var password: String?
)

fun UserAuthRequest.mapToUSerDto() =
        UserDto(
                this.email,
                this.password
        )
