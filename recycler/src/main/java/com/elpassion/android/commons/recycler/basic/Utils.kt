package com.elpassion.android.commons.recycler.basic


fun <Item> List<Item>.asBasicList(): BasicList<Item> = BasicListImpl(this)

class BasicListImpl<Item>(private val source: List<Item>) : BasicList<Item> {

    override fun get(key: Int) = source[key]

    override val size: Int get() = source.size
}


fun <Item> MutableList<Item>.asBasicMutableList(): BasicMutableList<Item> = BasicMutableListImpl(this)

class BasicMutableListImpl<Item>(private val source: MutableList<Item>) : BasicMutableList<Item> {

    override val size: Int get() = source.size

    override fun get(key: Int) = source[key]

    override fun set(key: Int, value: Item) {
        source[key] = value
    }

    override fun clear() = source.clear()

    override fun insert(index: Int, item: Item) = source.add(index, item)

    override fun remove(index: Int) {
        source.removeAt(index)
    }
}


fun <Item> BasicMutableList<Item>.add(item: Item) = insert(size, item)


fun <Item> BasicMutableList<Item>.addAll(items: Iterable<Item>) {
    for (item in items) add(item)
}


fun <K, V> Map<K, V>.asBasicMap() = object : BasicMap<K, V?> {
    override fun get(key: K) = this@asBasicMap[key]
}


fun <K, V> Map<K, List<V>>.asBasicMapOfBasicLists(): BasicMap<K, BasicList<V>?> = object : BasicMap<K, BasicList<V>?> {
    override fun get(key: K) = this@asBasicMapOfBasicLists[key]?.asBasicList()
}


fun <Item, Section> Map<Section, List<Item>>.asBasicListWithSections(): BasicListWithSections<Item, Section>
        = BasicListWithSectionsImpl(this)

class BasicListWithSectionsImpl<Item, Section>(private val source: Map<Section, List<Item>>) : BasicListWithSections<Item, Section> {

    override val sections = source.asBasicMapOfBasicLists()

    private val items = source.values.flatten()

    override fun get(key: Int) = items[key]

    override val size: Int get() = items.size
}


fun <Item, Section> MutableMap<Section, BasicMutableList<Item>>.asBasicListWithMutableSections(): BasicListWithMutableSections<Item, Section>
        = BasicListWithMutableSectionsImpl(this)

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
