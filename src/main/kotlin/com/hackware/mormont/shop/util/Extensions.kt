package com.hackware.mormont.shop.util

import com.hackware.mormont.shop.exception.EntityType
import com.hackware.mormont.shop.exception.ExceptionType
import com.hackware.mormont.shop.exception.LHException


/**
 * Returns a new RuntimeException
 *
 * @param entityType
 * @param exceptionType
 * @param args
 * @return
 */
fun exception(entityType: EntityType, exceptionType: ExceptionType, vararg args: String): RuntimeException {
    return LHException.throwException(entityType, exceptionType, *args)
}