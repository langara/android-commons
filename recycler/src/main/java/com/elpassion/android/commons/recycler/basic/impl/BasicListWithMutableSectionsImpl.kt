package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.BasicListWithMutableSections
import com.elpassion.android.commons.recycler.basic.BasicMutableList
import com.elpassion.android.commons.recycler.basic.BasicMutableMap

class BasicListWithMutableSectionsImpl<Item, Section>(private val source: MutableMap<Section, BasicMutableList<Item>>) : BasicListWithMutableSections<Item, Section> {

    override val size: Int get() = source.values.map { it.size }.sum()

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

    override val sections = Sections()

    inner class Sections : BasicMutableMap<Section, BasicMutableList<Item>?> {

        override fun set(key: Section, value: BasicMutableList<Item>?) {
            if (value === null) {
                source.remove(key)
            } else {
                source.put(key, value)
            }
        }

        override fun clear() = source.clear()

        override fun get(key: Section) = source[key]
    }
}