package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.BasicListWithSections
import com.elpassion.android.commons.recycler.basic.asBasicMapOfBasicLists

class BasicListWithSectionsImpl<Item, Section>(private val source: Map<Section, List<Item>>) : BasicListWithSections<Item, Section> {

    override val sections = source.asBasicMapOfBasicLists()

    private val items = source.values.flatten()

    override fun get(key: Int) = items[key]

    override val size: Int get() = items.size
}