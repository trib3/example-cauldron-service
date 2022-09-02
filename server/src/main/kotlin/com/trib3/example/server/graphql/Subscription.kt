package com.trib3.example.server.graphql

import com.trib3.example.api.models.Thing
import com.trib3.example.persistence.api.ThingDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Subscription
@Inject
constructor(
    private val thingDAO: ThingDAO
) : com.expediagroup.graphql.server.operations.Subscription {
    fun subscribe(): Flow<Thing> {
        return thingDAO.allFlow()
    }
}
