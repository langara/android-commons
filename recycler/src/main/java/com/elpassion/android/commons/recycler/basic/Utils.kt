package com.elpassion.android.commons.recycler.basic

import com.elpassion.android.commons.recycler.basic.impl.*

fun <K, V> Map<K, V>.asBasicMap(): BasicMap<K, V?> = BasicMapImpl(this)

fun <K, V> basicMapOf(vararg pairs: Pair<K, V>): BasicMap<K, V?> = BasicMapImpl(mapOf(*pairs))

fun <K, V> MutableMap<K, V>.asBasicMutableMap(): BasicMutableMap<K, V?> = BasicMutableMapImpl(this)

fun <K, V> basicMutableMapOf(vararg pairs: Pair<K, V>): BasicMutableMap<K, V?> = BasicMutableMapImpl(mutableMapOf(*pairs))

fun <Item> List<Item>.asBasicList(): BasicList<Item> = BasicListImpl(this)

fun <Item> MutableList<Item>.asBasicMutableList(): BasicMutableList<Item> = BasicMutableListImpl(this)

fun <Item> basicListOf(vararg items: Item): BasicList<Item> = BasicListImpl(listOf(*items))

fun <Item> basicMutableListOf(vararg items: Item): BasicMutableList<Item> = BasicMutableListImpl(mutableListOf(*items))

fun <K, V> Map<K, List<V>>.asBasicMapOfBasicLists(): BasicMap<K, BasicList<V>?> = object : BasicMap<K, BasicList<V>?> {
    override fun get(key: K) = this@asBasicMapOfBasicLists[key]?.asBasicList()
}

fun <Item, Section> Map<Section, List<Item>>.asBasicListWithSections(): BasicListWithSections<Item, Section>
        = BasicListWithSectionsImpl(this)

fun <Item, Section> MutableMap<Section, BasicMutableList<Item>>.asBasicListWithMutableSections(): BasicListWithMutableSections<Item, Section>
        = BasicListWithMutableSectionsImpl(this)

fun <Item> BasicMutableList<Item>.add(item: Item) = insert(size, item)

fun <Item> BasicMutableList<Item>.addAll(items: Iterable<Item>) {
    for (item in items) add(item)
}

