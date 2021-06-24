package org.skyfaced.todi.route.api.v1.user

import io.ktor.application.call
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import org.koin.ktor.ext.inject
import org.skyfaced.todi.models.user.UserInsert
import org.skyfaced.todi.services.public.PublicService
import org.skyfaced.todi.utils.getParam
import org.skyfaced.todi.utils.receiveAndValidate
import org.skyfaced.todi.utils.success

fun Route.publicRoute() {
    val service: PublicService by inject()

    post("/register") {
        val userInsert = call.receiveAndValidate<UserInsert>()
        val user = service.register(userInsert)
        call.success(message = "Register completed successfully", data = user)
    }

    get("/check-username") {
        val username = call.getParam<String>("username")
        val isUsernameAvailable = service.checkUsernameAvailability(username)
        call.success(message = "Username available", data = isUsernameAvailable)
    }
}
