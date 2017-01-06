package com.elpassion.android.commons.recycler.basic


interface BasicList<out Item> : BasicMap<Int, Item> {
    val size: Int
}
