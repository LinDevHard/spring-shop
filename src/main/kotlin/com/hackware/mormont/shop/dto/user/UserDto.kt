package com.hackware.mormont.shop.dto.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserDto(
        var email: String,
        @JsonIgnore
        var password: String?,
        var firstName: String,
        var lastName: String,
        var mobilePhone: String,
        var isAdmin: Boolean?,
        var userRoles: List<String> = listOf()
) {

    constructor(email: String?, password: String?) :
            this(email!!, password, "", "", "", false)

    fun getFullName() =
            "$firstName $lastName"

}