package com.trib3.example.persistence.modules

import com.trib3.db.modules.DbModule
import com.trib3.example.persistence.api.ThingDAO
import com.trib3.example.persistence.impl.ThingDAOJooq
import dev.misfitlabs.kotlinguice4.KotlinModule

/**
 * Binds DAO
 */
class ExamplePersistenceModule : KotlinModule() {
    override fun configure() {
        install(DbModule())
        bind<ThingDAO>().to<ThingDAOJooq>()
    }
}
