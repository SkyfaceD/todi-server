package org.skyfaced.todi.services.register

import org.skyfaced.todi.models.user.User
import org.skyfaced.todi.models.user.UserInsert
import org.skyfaced.todi.services.user.UserRepository

class RegisterServiceImpl(private val userRepository: UserRepository): RegisterService {
    override suspend fun register(userInsert: UserInsert): User {
        return userRepository.insertAndGetUser(userInsert)
    }
}