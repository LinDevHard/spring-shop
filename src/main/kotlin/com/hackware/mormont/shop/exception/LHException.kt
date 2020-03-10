package com.hackware.mormont.shop.exception

import com.hackware.mormont.shop.config.PropertiesConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.text.MessageFormat
import java.util.*


@Component
class LHException @Autowired constructor(propertiesConfig: PropertiesConfig) {

    class EntityNotFoundException(message: String?) : RuntimeException(message)
    class DuplicateEntityException(message: String?) : RuntimeException(message)

    companion object {
        var propertiesConfig: PropertiesConfig? = null

        /**
         * Returns new RuntimeException based on template and args
         *
         * @param messageTemplate
         * @param args
         * @return
         */
        fun throwException(messageTemplate: String, vararg args: String): RuntimeException {
            return RuntimeException(format(messageTemplate, *args))
        }

        /**
         * Returns new RuntimeException based on EntityType, ExceptionType and args
         *
         * @param entityType
         * @param exceptionType
         * @param args
         * @return
         */
        fun throwException(entityType: EntityType, exceptionType: ExceptionType, vararg args: String): RuntimeException {
            val messageTemplate = getMessageTemplate(entityType, exceptionType)
            return throwException(exceptionType, messageTemplate, *args)
        }

        /**
         * Returns new RuntimeException based on EntityType, ExceptionType and args
         *
         * @param entityType
         * @param exceptionType
         * @param args
         * @return
         */
        fun throwExceptionWithId(entityType: EntityType, exceptionType: ExceptionType, id: String, vararg args: String): RuntimeException {
            val messageTemplate = getMessageTemplate(entityType, exceptionType) + "." + id
            return throwException(exceptionType, messageTemplate, *args)
        }

        /**
         * Returns new RuntimeException based on EntityType, ExceptionType, messageTemplate and args
         *
         * @param entityType
         * @param exceptionType
         * @param messageTemplate
         * @param args
         * @return
         */
        fun throwExceptionWithTemplate(entityType: EntityType, exceptionType: ExceptionType, messageTemplate: String, vararg args: String): RuntimeException {
            return throwException(exceptionType, messageTemplate, *args)
        }

        /**
         * Returns new RuntimeException based on template and args
         *
         * @param messageTemplate
         * @param args
         * @return
         */
        private fun throwException(exceptionType: ExceptionType, messageTemplate: String, vararg args: String): RuntimeException =
                when (exceptionType) {
                    ExceptionType.ENTITY_NOT_FOUND -> EntityNotFoundException(format(messageTemplate, *args))
                    ExceptionType.DUPLICATE_ENTITY -> DuplicateEntityException(format(messageTemplate, *args))
                    else -> RuntimeException(format(messageTemplate, *args))
                }

        private fun getMessageTemplate(entityType: EntityType, exceptionType: ExceptionType): String =
                entityType.name + "." + exceptionType.value.toLowerCase()

        private fun format(template: String, vararg args: String): String {
            val templateContent: Optional<String> = Optional.ofNullable(propertiesConfig?.getConfigValue(template.toLowerCase()))
            return if (templateContent.isPresent) {
                MessageFormat.format(templateContent.get(), *args)
            } else String.format(template, *args)
        }

    }

    init {
        Companion.propertiesConfig = propertiesConfig
    }
}