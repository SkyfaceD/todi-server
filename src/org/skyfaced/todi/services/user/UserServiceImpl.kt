package org.skyfaced.todi.services.user

import org.skyfaced.todi.database.tables.user.UserEntity
import org.skyfaced.todi.exceptions.ResponseException
import java.util.UUID

class UserServiceImpl(private val repository: UserRepository) : UserService {
    override suspend fun getUserEntityByUsername(username: String): UserEntity {
        return repository.getUserEntityByUsername(username) ?: throw ResponseException("Can't find username")
    }

    override suspend fun validatePassword(userId: UUID, password: String): Boolean {
        return repository.validatePassword(userId, password)
    }
}
