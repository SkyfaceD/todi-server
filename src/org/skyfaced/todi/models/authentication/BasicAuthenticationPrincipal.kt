package org.skyfaced.todi.models.authentication

import io.ktor.auth.*
import java.util.*

data class BasicAuthenticationPrincipal(
    val id: UUID,
    val username: String
): Principal