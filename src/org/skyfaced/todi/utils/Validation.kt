package org.skyfaced.todi.utils

import io.konform.validation.Validation
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minLength
import io.konform.validation.jsonschema.pattern
import org.skyfaced.todi.models.user.UserInsert

object Validation {
    val userInsertValidation = Validation<UserInsert> {
        UserInsert::username {
            minLength(MIN_USERNAME_LENGTH).hint("Min length of username is $MIN_USERNAME_LENGTH")
            maxLength(MAX_USERNAME_LENGTH).hint("Max length of username is $MAX_USERNAME_LENGTH")
            pattern("^[^0-9]\\p{Alnum}+\$".toRegex()).hint("Invalid username pattern")
        }

        UserInsert::password {
            minLength(MIN_PASSWORD_LENGTH).hint("Min length of password is $MIN_PASSWORD_LENGTH")
            maxLength(MAX_PASSWORD_LENGTH).hint("Max length of password is $MAX_PASSWORD_LENGTH")
            pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$".toRegex()).hint("Invalid password pattern")
        }
    }
}