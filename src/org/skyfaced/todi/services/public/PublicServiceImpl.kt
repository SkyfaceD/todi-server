package org.skyfaced.todi.services.public

import org.skyfaced.todi.models.user.User
import org.skyfaced.todi.models.user.UserInsert
import org.skyfaced.todi.services.user.UserRepository

class PublicServiceImpl(private val userRepository: UserRepository): PublicService {
    override suspend fun register(userInsert: UserInsert): User {
        return userRepository.insertAndGetUser(userInsert)
    }

    override suspend fun checkUsernameAvailability(username: String): Boolean {
        return userRepository.getUserEntityByUsername(username) == null
    }
}