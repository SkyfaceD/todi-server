package org.skyfaced.todi.route.api.v1.user

import io.ktor.routing.Route
import io.ktor.routing.route
import org.koin.ktor.ext.inject
import org.skyfaced.todi.services.user.UserService

fun Route.userRoute() {
    val service: UserService by inject()

    route("/user") {
    }
}
