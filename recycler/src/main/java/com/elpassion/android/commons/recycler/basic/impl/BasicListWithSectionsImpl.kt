package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.BasicList
import com.elpassion.android.commons.recycler.basic.BasicListWithSections
import com.elpassion.android.commons.recycler.basic.basicMapOf

class BasicListWithSectionsImpl<Item, Section>(private val source: Map<Section, List<Item>>) : BasicListWithSections<Item, Section> {

    override val sections = basicMapOf<Section, BasicList<Item>>() // TODO

    override fun get(key: Int): Item {
        var offset = 0
        for (section in source.values) {
            if (key < offset + section.size) {
                return section[key - offset]
            } else {
                offset += section.size
            }
        }
        throw IndexOutOfBoundsException()
    }

    override val size: Int get() = source.map { it.value.size }.sum()
}