package com.trib3.example.server.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.trib3.example.api.models.Thing
import com.trib3.example.persistence.api.ThingDAO
import io.reactivex.rxkotlin.toFlowable
import javax.inject.Inject
import org.reactivestreams.Publisher

class Subscription
@Inject
constructor(
    private val thingDAO: ThingDAO
) : GraphQLQueryResolver {
    fun subscribe(): Publisher<Thing> {
        val stream = thingDAO.stream()
        return Iterable { stream.iterator() }.toFlowable()
            .doFinally(stream::close)
    }
}
