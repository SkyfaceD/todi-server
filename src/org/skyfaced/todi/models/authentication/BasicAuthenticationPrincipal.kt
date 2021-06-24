package org.skyfaced.todi.models.authentication

import io.ktor.auth.Principal
import java.util.UUID

data class BasicAuthenticationPrincipal(
    val id: UUID,
    val username: String
) : Principal
