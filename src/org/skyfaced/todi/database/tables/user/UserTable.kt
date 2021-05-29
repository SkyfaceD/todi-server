package org.skyfaced.todi.database.tables.user

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.jodatime.datetime

object UserTable: UUIDTable("user") {
    val username = varchar("username", 32).uniqueIndex("uq_user_username")
    val password = text("password").nullable()
    val name = varchar("name", 255).nullable()
    val isActive = bool("is_active")
    val createdAt = datetime("created_at")

    override val primaryKey by lazy { PrimaryKey(UserTable.id, name = "pk_user_id") }
}