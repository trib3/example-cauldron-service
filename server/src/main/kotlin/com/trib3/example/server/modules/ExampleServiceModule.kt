package com.trib3.example.server.modules

import com.trib3.example.persistence.modules.ExamplePersistenceModule
import com.trib3.example.server.graphql.Mutation
import com.trib3.example.server.graphql.Query
import com.trib3.server.modules.TribeApplicationModule

/**
 * Binds this service's resources
 */
class ExampleServiceModule : TribeApplicationModule() {
    override fun configure() {
        install(ExamplePersistenceModule())

        graphQLPackagesBinder().addBinding().toInstance("com.trib3.example.api")
        graphQLPackagesBinder().addBinding().toInstance("com.trib3.example.server.graphql")

        graphQLQueriesBinder().addBinding().to<Query>()
        graphQLMutationsBinder().addBinding().to<Mutation>()
    }
}
