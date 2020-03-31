package com.trib3.example.persistence.api

import com.trib3.example.api.models.Thing
import kotlinx.coroutines.flow.Flow

/**
 * DAO for managing Things
 */
interface ThingDAO {
    fun all(): List<Thing>
    fun allFlow(): Flow<Thing>
    fun get(id: Int): Thing?
    fun save(thing: Thing): Thing
}
