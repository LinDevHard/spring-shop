package com.hackware.mormont.shop.exception

enum class ExceptionType(var value: String) {
    ENTITY_NOT_FOUND("not.found"),
    DUPLICATE_ENTITY("duplicate"),
    ENTITY_EXCEPTION("exception"),
    INVALID_ENTITY_ID("invalid.id");

}