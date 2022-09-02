package com.trib3.example.server.graphql

import com.trib3.example.api.models.Thing
import com.trib3.example.persistence.api.ThingDAO
import javax.inject.Inject

/**
 * GraphQL entry point for [Thing] mutations.   Maps the DAO interfaces to the GraphQL models.
 */
class Mutation @Inject constructor(
    private val thingDAO: ThingDAO
) : com.expediagroup.graphql.server.operations.Mutation {
    fun thing(thing: Thing): Thing {
        return thingDAO.save(thing)
    }
}
