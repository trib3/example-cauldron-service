package com.trib3.example.server.graphql

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.trib3.example.api.models.Thing
import com.trib3.example.persistence.api.ThingDAO
import org.easymock.EasyMock
import org.testng.annotations.Test

class MutationTest {
    @Test
    fun testMutation() {
        val mockDAO = EasyMock.createMock<ThingDAO>(ThingDAO::class.java)
        EasyMock.expect(mockDAO.save(EasyMock.anyObject() ?: Thing(null, "")))
            .andReturn(Thing(1, "billy"))
        EasyMock.replay(mockDAO)
        val mutation = Mutation(mockDAO)
        val result = mutation.thing(Thing(null, "billy"))
        assertThat(result.id).isEqualTo(1)
        assertThat(result.name).isEqualTo("billy")
    }
}
