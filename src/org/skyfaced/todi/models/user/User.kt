package org.skyfaced.todi.models.user

data class User(
    val id: String,
    val username: String,
    val name: String?,
    val isActive: Boolean
)