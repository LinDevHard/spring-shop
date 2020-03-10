package com.hackware.mormont.shop.dto.command

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class AdminSignupFormCommand(

        @field:NotBlank(message = "{constraints.NotEmpty.message}")
        @field:Email
        var email: String?,

        @field:NotBlank(message = "Provide password!")
        @field:Size(min = 8, max = 32)
        var password: String?,

        @field:NotBlank(message = "Provide first name!")
        @field:Size(min = 3, max = 20)
        var firstName: String?,

        @field:NotBlank(message = "Provide last name!")
        @field:Size(min = 3, max = 20)
        var lastName: String?,

        @field:NotBlank(message = "{constraints.NotEmpty.message}")
        @field:Size(min = 11, max = 11, message = "Phone number is incorrect!")
        var phone: String?
) {
    constructor() : this(null, null, null, null, null)
}