package com.hackware.mormont.shop.dto.command

data class PasswordFormCommand(
        val email: String,
        val password: String
)