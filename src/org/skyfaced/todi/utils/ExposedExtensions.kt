package org.skyfaced.todi.utils

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.CustomStringFunction
import org.jetbrains.exposed.sql.stringLiteral
import org.skyfaced.todi.database.Crypt

fun crypt(password: String) = CustomStringFunction(
    "crypt",
    stringLiteral(password),
    CustomStringFunction(
        "gen_salt",
        stringLiteral("bf")
    )
)

fun Column<*>.crypt(password: String): Crypt = Crypt(password, this)
