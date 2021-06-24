package org.skyfaced.todi.utils

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import java.util.Properties

class ApplicationProperties {
    private val config = ConfigFactory.load("application.conf")

    val hikariProperties by lazy {
        val databaseConfig = config.getConfig("database")
        return@lazy databaseConfig.toProperties()
    }

    private fun Config.toProperties(): Properties {
        return Properties().apply {
            for (e in entrySet()) {
                setProperty(e.key, getString(e.key))
            }
        }
    }
}
