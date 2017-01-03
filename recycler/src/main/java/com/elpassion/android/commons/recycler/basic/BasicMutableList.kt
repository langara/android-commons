package com.elpassion.android.commons.recycler.basic


interface BasicMutableList<Item> : BasicList<Item>, BasicMutableMap<Int, Item> {
    fun insert(index: Int, item: Item)
    fun remove(index: Int)
}

