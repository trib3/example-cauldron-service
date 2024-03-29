package com.trib3.example.server.modules

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isInstanceOf
import com.trib3.example.server.graphql.Query
import com.trib3.graphql.modules.GraphQLApplicationModule.Companion.GRAPHQL_PACKAGES_BIND_NAME
import com.trib3.graphql.modules.GraphQLApplicationModule.Companion.GRAPHQL_QUERIES_BIND_NAME
import org.testng.annotations.Guice
import org.testng.annotations.Test
import javax.inject.Inject
import javax.inject.Named

@Guice(modules = [ExampleServiceModule::class])
class ExampleServiceModuleTest
@Inject constructor(
    @Named(GRAPHQL_PACKAGES_BIND_NAME)
    val packages: Set<String>,
    @Named(GRAPHQL_QUERIES_BIND_NAME)
    val queries: Set<Any>
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
