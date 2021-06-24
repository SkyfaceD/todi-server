package org.skyfaced.todi.services.user

import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.skyfaced.todi.database.TransactionService
import org.skyfaced.todi.database.tables.user.UserEntity
import org.skyfaced.todi.database.tables.user.UserTable
import org.skyfaced.todi.models.user.User
import org.skyfaced.todi.models.user.UserInsert
import org.skyfaced.todi.utils.crypt
import java.util.UUID

class UserRepository(private val transactionService: TransactionService) {
    suspend fun getUserEntityByUsername(username: String): UserEntity? = transactionService.transaction {
        return@transaction UserEntity
            .find { UserTable.username eq username }
            .firstOrNull()
    }

    /**
     * @return true if [password] equals with password in database, false if not
     */
    suspend fun validatePassword(id: UUID, password: String): Boolean = transactionService.transaction {
        val match = UserTable.password.crypt(password)
        return@transaction UserTable
            .slice(match)
            .select { UserTable.id eq id }
            .map { row -> row[match] }
            .firstOrNull() ?: false
    }

    suspend fun insertAndGetUser(userInsert: UserInsert): User = transactionService.transaction {
        val userId = UserTable.insertAndGetId { statement ->
            statement[username] = userInsert.username
            statement[password] = crypt(userInsert.password)
        }

        return@transaction UserEntity[userId].toUser()
    }
}
