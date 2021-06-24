package org.skyfaced.todi.database

import org.jetbrains.exposed.sql.Transaction

class TransactionServiceImpl(private val databaseConnector: DatabaseConnector) : TransactionService {
    override suspend fun <T> newTransaction(block: suspend () -> T): T {
        return databaseConnector.newTransaction {
            block()
        }
    }

    override suspend fun <T> existingTransaction(block: suspend () -> T): T {
        return databaseConnector.existingTransaction {
            block()
        }
    }

    override suspend fun <T> transaction(block: suspend (transaction: Transaction) -> T): T {
        return databaseConnector.transaction {
            block(it)
        }
    }
}
