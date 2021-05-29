package org.skyfaced.todi.utils

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import org.skyfaced.todi.exceptions.ResponseException
import org.skyfaced.todi.models.ReceiveValidator
import org.skyfaced.todi.models.authentication.BasicAuthenticationPrincipal
import org.skyfaced.todi.models.response.Response

suspend fun <T> ApplicationCall.respond(
    statusCode: HttpStatusCode,
    response: Response<T>,
) {
    respond(status = statusCode, message = response)
}

suspend fun <T> ApplicationCall.success(
    statusCode: HttpStatusCode = HttpStatusCode.OK,
    message: String? = null,
    data: T? = null
) {
    respond(statusCode = statusCode, response = Response.Success(message, data))
}

suspend fun ApplicationCall.error(
    statusCode: HttpStatusCode = HttpStatusCode.BadRequest,
    message: String? = null,
    stackTrace: String? = null
) {
    respond(statusCode = statusCode, response = Response.Error(message, stackTrace))
}

suspend fun <T> ApplicationCall.warning(
    statusCode: HttpStatusCode = HttpStatusCode.OK,
    message: String? = null,
    data: T? = null
) {
    respond(statusCode = statusCode, response = Response.Warning(message, data))
}

fun ApplicationCall.basicAuthenticationPrincipal(): BasicAuthenticationPrincipal {
    return principal() ?: throw ResponseException(
        message = "Authentication principal not found",
        statusCode = HttpStatusCode.Unauthorized
    )
}

suspend inline fun <reified T> ApplicationCall.receiveOrException(): T {
    return receiveOrNull() ?: throw ResponseException("Fill payload")
}

suspend inline fun <reified T : ReceiveValidator> ApplicationCall.receiveAndValidate(): T {
    return receiveOrException<T>().validate()
}