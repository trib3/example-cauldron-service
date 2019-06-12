package com.trib3.example.persistence.api

import com.trib3.example.api.models.Thing

/**
 * DAO for managing Things
 */
interface ThingDAO {
    fun all(): List<Thing>
    fun get(id: Int): Thing?
    fun save(thing: Thing): Thing
}
