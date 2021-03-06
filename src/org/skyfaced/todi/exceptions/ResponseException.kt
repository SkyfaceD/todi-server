package org.skyfaced.todi.exceptions

import io.ktor.http.HttpStatusCode

open class ResponseException(
    override val message: String?,
    val statusCode: HttpStatusCode? = null,
    val withStackTrace: Boolean = false
) : RuntimeException(message)
