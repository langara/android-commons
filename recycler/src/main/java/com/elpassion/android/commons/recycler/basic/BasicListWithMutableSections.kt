package com.elpassion.android.commons.recycler.basic

interface BasicListWithMutableSections<Item, Section> : BasicList<Item> {
    val sections: BasicMutableMap<Section, BasicMutableList<Item>?>
}