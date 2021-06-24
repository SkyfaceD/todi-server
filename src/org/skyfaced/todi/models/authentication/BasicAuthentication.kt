package org.skyfaced.todi.models.authentication

import org.skyfaced.todi.models.user.User

data class BasicAuthentication(
    val token: String,
    val user: User
)
