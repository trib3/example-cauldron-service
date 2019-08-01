package com.trib3.example.persistence.api

import com.trib3.example.api.models.Thing
import java.util.stream.Stream

/**
 * DAO for managing Things
 */
interface ThingDAO {
    fun all(): List<Thing>
    fun stream(): Stream<Thing>
    fun get(id: Int): Thing?
    fun save(thing: Thing): Thing
}
