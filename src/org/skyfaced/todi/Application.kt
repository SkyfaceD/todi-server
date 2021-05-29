package org.skyfaced.todi

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.server.jetty.*
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.koin.logger.SLF4JLogger
import org.skyfaced.todi.di.modules.applicationModule
import org.skyfaced.todi.di.modules.serviceModule
import org.skyfaced.todi.exceptions.ResponseException
import org.skyfaced.todi.models.authentication.BasicAuthenticationPrincipal
import org.skyfaced.todi.route.api.v1.apiV1
import org.skyfaced.todi.services.user.UserService
import org.skyfaced.todi.utils.BASIC_AUTHENTICATION
import org.skyfaced.todi.utils.error
import org.slf4j.event.Level

fun main(args: Array<String>) {
    EngineMain.main(args)
}

@Suppress("unused")
fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders)

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(Koin) {
        SLF4JLogger()
        modules(applicationModule, serviceModule)
    }

    install(ContentNegotiation) {
        gson {
            serializeNulls()
        }
    }

    install(CORS) {
        method(HttpMethod.Put)
        method(HttpMethod.Patch)
        method(HttpMethod.Delete)
        header(HttpHeaders.Authorization)
        allowCredentials = true
    }

    install(Authentication) {
        val service: UserService by inject()
        basic(BASIC_AUTHENTICATION) {
            realm = "Access to whole API"
            validate { credential ->
                val userEntity = service.getUserEntityByUsername(credential.name)
                if (!userEntity.isActive) throw ResponseException("Your account is blocked")
                if (!service.validatePassword(userEntity.id.value, credential.password))
                    throw ResponseException("Password not valid")
                return@validate BasicAuthenticationPrincipal(userEntity.id.value, userEntity.username)
            }
        }
    }

    install(StatusPages) {
        exception<ResponseException> { e ->
            call.error(
                statusCode = e.statusCode ?: HttpStatusCode.BadRequest,
                message = e.message,
                stackTrace = if (e.withStackTrace) e.stackTraceToString() else null
            )
        }

        exception<Exception> { e ->
            call.error(message = "Something went wrong", stackTrace = e.stackTraceToString())
        }
    }

    install(Routing) {
        apiV1()
    }
}
