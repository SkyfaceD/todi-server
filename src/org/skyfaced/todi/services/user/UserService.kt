package org.skyfaced.todi.services.user

import org.skyfaced.todi.database.tables.user.UserEntity
import java.util.UUID

interface UserService {
    suspend fun getUserEntityByUsername(username: String): UserEntity

    suspend fun validatePassword(userId: UUID, password: String): Boolean
}
