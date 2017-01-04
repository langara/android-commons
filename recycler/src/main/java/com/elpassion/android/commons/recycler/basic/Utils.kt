package com.elpassion.android.commons.recycler.basic

import com.elpassion.android.commons.recycler.basic.impl.BasicListImpl
import com.elpassion.android.commons.recycler.basic.impl.BasicListWithMutableSectionsImpl
import com.elpassion.android.commons.recycler.basic.impl.BasicListWithSectionsImpl
import com.elpassion.android.commons.recycler.basic.impl.BasicMutableListImpl


fun <Item> List<Item>.asBasicList(): BasicList<Item> = BasicListImpl(this)

fun <Item> MutableList<Item>.asBasicMutableList(): BasicMutableList<Item> = BasicMutableListImpl(this)

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

fun <Item, Section> MutableMap<Section, BasicMutableList<Item>>.asBasicListWithMutableSections(): BasicListWithMutableSections<Item, Section>
        = BasicListWithMutableSectionsImpl(this)

