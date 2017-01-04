package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.BasicList

class BasicListImpl<Item>(private val source: List<Item>) : BasicList<Item> {

    override fun get(key: Int) = source[key]

    override val size: Int get() = source.size
}