package org.skyfaced.todi.route.api.v1.user

import io.ktor.application.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import org.skyfaced.todi.models.user.UserInsert
import org.skyfaced.todi.services.register.RegisterService
import org.skyfaced.todi.utils.receiveAndValidate
import org.skyfaced.todi.utils.success

fun Route.registerRoute() {
    val service: RegisterService by inject()

    post("/register") {
        val userInsert = call.receiveAndValidate<UserInsert>()
        val user = service.register(userInsert)
        call.success(message = "Register completed successfully", data = user)
    }
}