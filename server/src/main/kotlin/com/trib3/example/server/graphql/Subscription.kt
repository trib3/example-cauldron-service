package com.trib3.example.server.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.trib3.example.api.models.Thing
import com.trib3.example.persistence.api.ThingDAO
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.reactive.asPublisher
import org.reactivestreams.Publisher

@UseExperimental(ExperimentalCoroutinesApi::class)
class Subscription
@Inject
constructor(
    private val thingDAO: ThingDAO
) : GraphQLQueryResolver {
    fun subscribe(): Publisher<Thing> {
        val stream = thingDAO.stream()
        return stream.iterator().asFlow()
            .onCompletion { stream.close() }
            .asPublisher()
    }
}
