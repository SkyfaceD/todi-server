package org.skyfaced.todi.models.authentication

import io.ktor.auth.*

data class BasicAuthenticationCredential(
    val username: String,
    val password: String
) : Credential