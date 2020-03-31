package com.trib3.example.server.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.trib3.example.api.models.Thing
import com.trib3.example.persistence.api.ThingDAO
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalCoroutinesApi::class)
class Subscription
@Inject
constructor(
    private val thingDAO: ThingDAO
) : GraphQLQueryResolver {
    fun subscribe(): Flow<Thing> {
        return thingDAO.allFlow()
    }
}
