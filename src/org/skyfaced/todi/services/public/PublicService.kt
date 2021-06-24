package org.skyfaced.todi.services.public

import org.skyfaced.todi.models.user.User
import org.skyfaced.todi.models.user.UserInsert

interface PublicService {
    suspend fun register(userInsert: UserInsert): User

    suspend fun checkUsernameAvailability(username: String): Boolean
}
