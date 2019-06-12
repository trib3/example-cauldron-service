package com.trib3.example.persistence.impl

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.doesNotContain
import assertk.assertions.isEqualTo
import com.trib3.db.DAOTestBase
import com.trib3.example.api.models.Thing
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * Test the ThingDAO
 */
class ThingDAOTest : DAOTestBase() {
    lateinit var dao: ThingDAOJooq

    @BeforeClass
    override fun setUp() {
        super.setUp()
        dao = ThingDAOJooq(ctx)
    }

    @Test
    fun testRoundTrip() {
        val thing = Thing(null, "billy")
        val nextThing = Thing(null, "jimmy")
        dao.save(thing)
        dao.save(nextThing)
        assertThat(dao.get(1)?.name).isEqualTo(thing.name)
        assertThat(dao.all().map { it.name }).all {
            contains(thing.name)
            contains(nextThing.name)
        }
        val updateThing = Thing(1, "william")
        dao.save(updateThing)
        assertThat(dao.all().map { it.name }).all {
            contains(updateThing.name)
            doesNotContain(thing.name)
            contains(nextThing.name)
        }
    }
}
