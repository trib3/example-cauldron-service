package com.trib3.example.persistence.modules

import assertk.assertThat
import assertk.assertions.isNotNull
import com.trib3.db.config.DbConfig
import com.trib3.example.persistence.api.ThingDAO
import javax.inject.Inject
import org.jooq.DSLContext
import org.testng.annotations.Guice
import org.testng.annotations.Test

@Guice(modules = [ExamplePersistenceModule::class])
class ExamplePersistenceModuleTest
@Inject constructor(
    val dao: ThingDAO,
    val ctx: DSLContext,
    val config: DbConfig
) {
    @Test
    fun testInjection() {
        assertThat(dao).isNotNull()
        assertThat(ctx).isNotNull()
        assertThat(config).isNotNull()
    }
}
