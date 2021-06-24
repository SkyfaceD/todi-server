package org.skyfaced.todi.database.tables.user

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.skyfaced.todi.models.user.User
import java.util.UUID

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    // @formatter:off
    var username by UserTable.username
    var password by UserTable.password
    var name by UserTable.name
    var isActive by UserTable.isActive
    var createdAt by UserTable.createdAt
    // @formatter:on

    companion object : UUIDEntityClass<UserEntity>(UserTable)

    fun toUser() = User(
        id = id.value.toString(),
        username = username,
        name = name,
        isActive = isActive
    )
}
