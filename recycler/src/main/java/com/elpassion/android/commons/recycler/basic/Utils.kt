package com.elpassion.android.commons.recycler.basic

fun <Item> List<Item>.asBasicList() = object : BasicList<Item> {

    override fun get(key: Int) = this@asBasicList[key]

    override val size: Int get() = this@asBasicList.size
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
