package com.trib3.example.server.graphql

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.trib3.example.api.models.Thing
import com.trib3.example.persistence.api.ThingDAO
import io.reactivex.subscribers.TestSubscriber
import org.easymock.EasyMock
import org.testng.annotations.Test

class SubscriptionTest {
    @Test
    fun testSubscription() {
        val mockData = listOf(
            Thing(1, "billy"),
            Thing(2, "jimmy"),
            Thing(3, "sammy")
        )
        val mockDAO = EasyMock.createMock<ThingDAO>(ThingDAO::class.java)
        EasyMock.expect(mockDAO.stream()).andReturn(mockData.stream())
        EasyMock.replay(mockDAO)
        val query = Subscription(mockDAO)
        val result = query.subscribe()
        val subscriber = TestSubscriber<Thing>()
        result.subscribe(subscriber)
        subscriber.await()
        assertThat(subscriber.values()).isEqualTo(mockData)
    }
}
