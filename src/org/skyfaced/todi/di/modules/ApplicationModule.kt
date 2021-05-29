package org.skyfaced.todi.di.modules

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy
import org.skyfaced.todi.database.DatabaseConnector
import org.skyfaced.todi.database.TransactionService
import org.skyfaced.todi.database.TransactionServiceImpl
import org.skyfaced.todi.utils.ApplicationProperties
import javax.sql.DataSource

@Suppress("RemoveExplicitTypeArguments")
val applicationModule = module(createdAtStart = true) {
    single { ApplicationProperties() }

    single<DataSource> { HikariDataSource(HikariConfig(get<ApplicationProperties>().hikariProperties)) }

    single<DatabaseConnector> { DatabaseConnector(dataSource = get()) }

    singleBy<TransactionService, TransactionServiceImpl>()
}