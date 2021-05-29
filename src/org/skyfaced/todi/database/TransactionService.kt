package org.skyfaced.todi.database

import org.jetbrains.exposed.sql.Transaction

interface TransactionService {
    suspend fun <T> newTransaction(block: suspend () -> T): T

    suspend fun <T> existingTransaction(block: suspend () -> T): T

    suspend fun <T> transaction(block: suspend (transaction: Transaction) -> T): T
}