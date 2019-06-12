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

        graphqlPackagesBinder().addBinding().toInstance("com.trib3.example.api")
        graphqlPackagesBinder().addBinding().toInstance("com.trib3.example.server.graphql")

        graphqlQueriesBinder().addBinding().to<Query>()
        graphqlMutationsBinder().addBinding().to<Mutation>()
    }
}
