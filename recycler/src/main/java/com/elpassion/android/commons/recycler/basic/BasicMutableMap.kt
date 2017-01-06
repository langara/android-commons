package com.elpassion.android.commons.recycler.basic


interface BasicMutableMap<in Key, Value> : BasicMap<Key, Value> {
    operator fun set(key: Key, value: Value)
    fun clear()
}