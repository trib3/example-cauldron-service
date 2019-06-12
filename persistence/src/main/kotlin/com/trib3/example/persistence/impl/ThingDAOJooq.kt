package com.trib3.example.persistence.impl

import com.codahale.metrics.annotation.Timed
import com.trib3.example.api.models.Thing
import com.trib3.example.persistence.api.ThingDAO
import com.trib3.example.persistence.impl.jooq.Tables
import com.trib3.example.persistence.impl.jooq.tables.records.ThingsRecord
import mu.KotlinLogging
import org.jooq.DSLContext
import javax.inject.Inject
import kotlin.streams.toList

private val log = KotlinLogging.logger { }

/**
 * DAO implementation for Things
 */
open class ThingDAOJooq
@Inject constructor(
    private val ctx: DSLContext
) : ThingDAO {
    private fun getRecord(dsl: DSLContext, id: Int): ThingsRecord {
        return dsl.selectFrom(Tables.THINGS).where(Tables.THINGS.ID.eq(id)).fetchOne()
    }

    @Timed
    override fun get(id: Int): Thing? {
        return getRecord(ctx, id).into(Thing::class.java)
    }

    @Timed
    override fun save(thing: Thing): Thing {
        return ctx.transactionResult { config ->
            val thingId = thing.id
            val record = if (thingId == null) {
                config.dsl().newRecord(
                    Tables.THINGS,
                    thing
                )
            } else {
                val existing = getRecord(config.dsl(), thingId)
                existing.from(thing)
                existing
            }
            record.store()
            record.into(Thing::class.java)
        }
    }

    @Timed
    override fun all(): List<Thing> {
        return ctx.select().from(Tables.THINGS).fetchStreamInto(Thing::class.java).toList()
    }
}
