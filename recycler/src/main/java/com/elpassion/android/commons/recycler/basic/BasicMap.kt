package com.elpassion.android.commons.recycler.basic


interface BasicMap<in Key, out Value> {
    operator fun get(key: Key): Value
}