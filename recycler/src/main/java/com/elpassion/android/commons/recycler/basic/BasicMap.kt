package com.elpassion.android.commons.recycler.basic


interface BasicMap<Key, Value> {
    operator fun get(key: Key): Value
}