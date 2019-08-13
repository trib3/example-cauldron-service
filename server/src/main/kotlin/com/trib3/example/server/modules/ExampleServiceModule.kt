package com.trib3.example.server.modules

import com.trib3.example.persistence.modules.ExamplePersistenceModule
import com.trib3.example.server.graphql.Mutation
import com.trib3.example.server.graphql.Query
import com.trib3.example.server.graphql.Subscription
import com.trib3.graphql.modules.GraphQLApplicationModule

/**
 * Binds this service's resources
 */
class ExampleServiceModule : GraphQLApplicationModule() {
    override fun configureApplication() {
        install(ExamplePersistenceModule())

        graphQLPackagesBinder().addBinding().toInstance("com.trib3.example.api")
        graphQLPackagesBinder().addBinding().toInstance("com.trib3.example.server.graphql")

        graphQLQueriesBinder().addBinding().to<Query>()
        graphQLMutationsBinder().addBinding().to<Mutation>()
        graphQLSubscriptionsBinder().addBinding().to<Subscription>()
    }
}
