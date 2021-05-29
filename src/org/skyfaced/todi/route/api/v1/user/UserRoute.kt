package org.skyfaced.todi.route.api.v1.user

import io.ktor.application.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import org.skyfaced.todi.models.user.UserInsert
import org.skyfaced.todi.services.user.UserService
import org.skyfaced.todi.utils.receiveAndValidate
import org.skyfaced.todi.utils.success

fun Route.userRoute() {
    val service: UserService by inject()

    route("/user") {
    }
}