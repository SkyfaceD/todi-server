package org.skyfaced.todi.services.user

import org.skyfaced.todi.database.tables.user.UserEntity
import org.skyfaced.todi.models.user.User
import org.skyfaced.todi.models.user.UserInsert
import java.util.*

interface UserService {
    suspend fun getUserEntityByUsername(username: String): UserEntity

    suspend fun validatePassword(userId: UUID, password: String): Boolean
}