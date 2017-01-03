package com.elpassion.android.commons.recycler.basic

interface BasicListWithSections<Item, Section> : BasicList<Item> {
    val sections: BasicMap<Section, BasicList<Item>?>
}