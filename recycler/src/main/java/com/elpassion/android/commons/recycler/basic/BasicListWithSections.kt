package com.elpassion.android.commons.recycler.basic

interface BasicListWithSections<out Item, in Section> : BasicList<Item> {
    val sections: BasicMap<Section, BasicList<Item>?>
}