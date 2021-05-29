package org.skyfaced.todi.route.api.v1

import io.ktor.auth.*
import io.ktor.routing.*
import org.skyfaced.todi.route.api.v1.user.publicRoute
import org.skyfaced.todi.route.api.v1.user.userRoute
import org.skyfaced.todi.utils.BASIC_AUTHENTICATION

fun Route.apiV1() {
    route("/api/v1") {
        publicRoute()

        authenticate(BASIC_AUTHENTICATION) {
            userRoute()
        }
    }
}