package com.hackware.mormont.shop.exception

import com.hackware.mormont.shop.dto.response.Response
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ConstraintViolationException


@ControllerAdvice
@RestController
class CustomResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {


    @ExceptionHandler(LHException.EntityNotFoundException::class)
    fun handleNotFountExceptions(ex: Exception, request: WebRequest): ResponseEntity<*> {
        val response: Response<Any> = Response.notFound()
        response.addErrorMsgToResponse(ex.message, ex)
        return ResponseEntity<Any>(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(LHException.DuplicateEntityException::class)
    fun handleNotFountExceptions1(ex: java.lang.Exception, request: WebRequest): ResponseEntity<*> {
        val response: Response<Any> = Response.duplicateEntity()
        response.addErrorMsgToResponse(ex.message, ex)
        return ResponseEntity<Any>(response, HttpStatus.CONFLICT)
    }

    override fun handleHttpRequestMethodNotSupported(ex: HttpRequestMethodNotSupportedException,
                                                     headers: HttpHeaders, status: HttpStatus,
                                                     request: WebRequest): ResponseEntity<Any> {
        val response: Response<Any> = Response.methodNotAllowed()
        response.addErrorMsgToResponse(ex.message, ex)
        return ResponseEntity<Any>(response, HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException, request: WebRequest): ResponseEntity<*>? {
        val response: Response<Any> = Response.validationException()
        response.addErrorMsgToResponse(ex.message, ex)
        return ResponseEntity<Any>(response, HttpStatus.CONFLICT)
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders,
                                              status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val response: Response<Any> = Response.validationException()
        val errors = ex.bindingResult.fieldErrors.map { it.field + ": " + it.defaultMessage }
        response.addErrorMsgToResponse(errors.toString(), ex)
        return ResponseEntity<Any>(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthenticationException(ex: AuthenticationException): ResponseEntity<Any> {
        val response: Response<Any> = Response.unauthorized()
        response.addErrorMsgToResponse(ex.message, ex)
        return ResponseEntity<Any>(response, HttpStatus.UNAUTHORIZED)
    }
}