package com.elpassion.android.commons.recycler.basic


interface BasicList<Item> : BasicMap<Int, Item> {
    val size: Int
}

fun <Item> List<Item>.asBasicList() = object : BasicList<Item> {

    override fun get(key: Int): Item = this@asBasicList[key]

    override val size: Int get() = this@asBasicList.size
}