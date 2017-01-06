package com.elpassion.android.commons.recycler.basic

interface BasicListWithMutableSections<Item, in Section> : BasicListWithSections<Item, Section> {
    override val sections: BasicMutableMap<Section, BasicMutableList<Item>?>
}