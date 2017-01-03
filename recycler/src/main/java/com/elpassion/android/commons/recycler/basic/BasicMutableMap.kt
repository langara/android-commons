package com.elpassion.android.commons.recycler.basic


interface BasicMutableMap<Key, Value> : BasicMap<Key, Value> {
    operator fun set(key: Key, value: Value)
}