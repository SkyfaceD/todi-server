package org.skyfaced.todi.services.register

import org.skyfaced.todi.models.user.User
import org.skyfaced.todi.models.user.UserInsert

interface RegisterService {
    suspend fun register(userInsert: UserInsert): User
}