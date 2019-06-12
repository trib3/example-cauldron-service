package com.trib3.example.persistence.modules

import com.authzee.kotlinguice4.KotlinModule
import com.trib3.db.modules.DbModule
import com.trib3.example.persistence.api.ThingDAO
import com.trib3.example.persistence.impl.ThingDAOJooq

/**
 * Binds DAO
 */
class ExamplePersistenceModule : KotlinModule() {
    override fun configure() {
        install(DbModule())
        bind<ThingDAO>().to<ThingDAOJooq>()
    }
}
