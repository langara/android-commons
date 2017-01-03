package com.elpassion.android.commons.recycler.basic

fun <Item> List<Item>.asBasicList() = object : BasicList<Item> {

    override fun get(key: Int) = this@asBasicList[key]

    override val size: Int get() = this@asBasicList.size
}

fun <Item> MutableList<Item>.asBasicMutableList() = object : BasicMutableList<Item> {

    override val size: Int get() = this@asBasicMutableList.size

    override fun get(key: Int) = this@asBasicMutableList[key]

    override fun set(key: Int, value: Item) {
        this@asBasicMutableList[key] = value
    }

    override fun clear() = this@asBasicMutableList.clear()

    override fun insert(index: Int, item: Item) = add(index, item)

    override fun remove(index: Int) {
        removeAt(index)
    }
}

fun <Item> BasicMutableList<Item>.add(item: Item) = insert(size, item)

fun <Item> BasicMutableList<Item>.addAll(items: Iterable<Item>) {
    for(item in items) add(item)
}


fun <K, V> Map<K, V>.asBasicMap() = object : BasicMap<K, V?> {
    override fun get(key: K) = this@asBasicMap[key]
}

fun <K, V> Map<K, List<V>>.asBasicMapOfBasicLists() = object : BasicMap<K, BasicList<V>?> {
    override fun get(key: K) = this@asBasicMapOfBasicLists[key]?.asBasicList()
}

fun <Section, Item> Map<Section, List<Item>>.asBasicListWithSections() = object : BasicListWithSections<Item, Section> {

    override val sections = this@asBasicListWithSections.asBasicMapOfBasicLists()

    private val items = this@asBasicListWithSections.values.flatten()

    override fun get(key: Int) = items[key]

    override val size: Int get() = items.size
}

fun <Section, Item> MutableMap<Section, BasicMutableList<Item>>.asBasicListWithMutableSections() = object : BasicListWithMutableSections<Item, Section> {

    override val size: Int get() = values.map { it.size }.sum()

    override fun get(key: Int): Item {
        var offset = 0
        for (section in values) {
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
                remove(key)
            } else {
                put(key, value)
            }
        }

        override fun clear() = this@asBasicListWithMutableSections.clear()

        override fun get(key: Section) = this@asBasicListWithMutableSections[key]
    }
}
