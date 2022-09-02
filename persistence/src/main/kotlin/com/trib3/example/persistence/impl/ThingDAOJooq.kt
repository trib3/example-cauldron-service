package com.trib3.example.persistence.impl

import com.codahale.metrics.annotation.Timed
import com.trib3.db.jooqext.consumeAsFlow
import com.trib3.example.api.models.Thing
import com.trib3.example.persistence.api.ThingDAO
import com.trib3.example.persistence.impl.jooq.Tables
import com.trib3.example.persistence.impl.jooq.tables.records.ThingsRecord
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.jooq.DSLContext
import javax.inject.Inject

/**
 * DAO implementation for Things
 */
open class ThingDAOJooq
constructor(
    private val ctx: DSLContext,
    private val dispatcher: CoroutineDispatcher
) : ThingDAO {
    @Suppress("InjectDispatcher")
    // https://github.com/detekt/detekt/issues/5225
    @Inject
    constructor(ctx: DSLContext) : this(ctx, Dispatchers.IO)

    private fun getRecord(dsl: DSLContext, id: Int): ThingsRecord? {
        return dsl.selectFrom(Tables.THINGS).where(Tables.THINGS.ID.eq(id)).fetchOne()
    }

    @Timed
    override fun get(id: Int): Thing? {
        return getRecord(ctx, id)?.into(Thing::class.java)
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
                if (existing != null) {
                    existing.from(thing)
                    existing
                } else {
                    config.dsl().newRecord(
                        Tables.THINGS,
                        thing
                    )
                }
            }
            record.store()
            record.into(Thing::class.java)
        }
    }

    @Timed
    override fun all(): List<Thing> {
        return ctx.select().from(Tables.THINGS).fetchInto(Thing::class.java)
    }

    @Timed
    override fun allFlow(): Flow<Thing> {
        return ctx.select().from(Tables.THINGS).consumeAsFlow(dispatcher)
            .map { it.into(Thing::class.java) }
            .flowOn(dispatcher)
    }
}
