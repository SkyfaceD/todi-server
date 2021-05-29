package org.skyfaced.todi.models.user

import org.skyfaced.todi.exceptions.ResponseException
import org.skyfaced.todi.models.ReceiveValidator
import org.skyfaced.todi.utils.Validation

data class UserInsert(
    val username: String,
    val password: String
) : ReceiveValidator {
    @Suppress("UNCHECKED_CAST")
    override fun <T> validate(): T {
        val validation = Validation.userInsertValidation
        val result = validation.validate(this)
        if (!result.errors.isNullOrEmpty()) throw ResponseException(result.errors.first().message)
        return this as T
    }
}