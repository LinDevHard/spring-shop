package com.hackware.mormont.shop.dto.response

import com.fasterxml.jackson.annotation.JsonInclude


@JsonInclude(JsonInclude.Include.NON_NULL)
data class AuthSuccessResponse(
        var accessToken: String?
)