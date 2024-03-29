package com.trib3.example.server.graphql

import com.trib3.example.api.models.Thing
import com.trib3.example.persistence.api.ThingDAO
import javax.inject.Inject

/**
 * GraphQL entry point for [Thing] queries.  Maps the DAO interfaces to the GraphQL models.
 */
class Query @Inject constructor(
    private val thingDAO: ThingDAO
) : com.expediagroup.graphql.server.operations.Query {
    fun thing(id: Int): Thing? {
        return thingDAO.get(id)
    }
}
