package com.hackware.mormont.shop.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Response<T>(
        var success: Boolean = false,
        var status: Status? = null,
        var data: T? = null,
        var message: String? = null,
        var metadata: Any? = null
) {

    fun addErrorMsgToResponse(errorMsg: String?, ex: Exception) {
        message = errorMsg
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    class PageMetadata(private val size: Int, private val totalElements: Long, private val totalPages: Int, private val number: Int)

    companion object {
        fun <T> badRequest(): Response<T> {
            val response: Response<T> = Response()
            response.status = (Status.BAD_REQUEST)
            return response
        }

        fun <T> ok(): Response<T> {
            val response: Response<T> = Response()
            response.success = true
            response.status = Status.OK
            return response
        }

        fun <T> unauthorized(): Response<T> {
            val response: Response<T> = Response()
            response.status = (Status.UNAUTHORIZED)
            return response
        }

        fun <T> validationException(): Response<T> {
            val response: Response<T> = Response()
            response.status = (Status.VALIDATION_EXCEPTION)
            return response
        }

        fun <T> wrongCredentials(): Response<T> {
            val response: Response<T> = Response()
            response.status = (Status.WRONG_CREDENTIALS)
            return response
        }

        fun <T> accessDenied(): Response<T> {
            val response: Response<T> = Response()
            response.status = (Status.ACCESS_DENIED)
            return response
        }

        fun <T> exception(): Response<T> {
            val response: Response<T> = Response()
            response.status = (Status.EXCEPTION)
            return response
        }

        fun <T> notFound(): Response<T> {
            val response: Response<T> = Response()
            response.status = (Status.NOT_FOUND)
            return response
        }

        fun <T> duplicateEntity(): Response<T> {
            val response: Response<T> = Response()
            response.status = (Status.DUPLICATE_ENTITY)
            return response
        }

        fun <T> methodNotAllowed(): Response<T> {
            val response: Response<T> = Response()
            response.status = (Status.METHOD_NOT_ALLOWED)
            return response
        }
    }
}
