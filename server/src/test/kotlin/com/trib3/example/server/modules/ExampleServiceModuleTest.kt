package com.trib3.example.server.modules

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isInstanceOf
import com.trib3.example.server.graphql.Query
import com.trib3.server.modules.TribeApplicationModule
import org.testng.annotations.Guice
import org.testng.annotations.Test
import javax.inject.Inject
import javax.inject.Named

@Guice(modules = [ExampleServiceModule::class])
class ExampleServiceModuleTest
@Inject constructor(
    @Named(TribeApplicationModule.GRAPHQL_PACKAGES_BIND_NAME)
    val packages: Set<@JvmSuppressWildcards String>,
    @Named(TribeApplicationModule.GRAPHQL_QUERIES_BIND_NAME)
    val queries: Set<@JvmSuppressWildcards Any>
) {
    @Test
    fun testResources() {
        assertThat(packages).all {
            contains("com.trib3.example.api")
            contains("com.trib3.example.server.graphql")
        }
        assertThat(queries.first()).isInstanceOf(Query::class)
    }
}
