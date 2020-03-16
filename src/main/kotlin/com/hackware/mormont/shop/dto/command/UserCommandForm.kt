package com.hackware.mormont.shop.dto.command

import com.hackware.mormont.shop.dto.user.UserDto

data class UserCommandForm(
        val users: List<UserDto>
)