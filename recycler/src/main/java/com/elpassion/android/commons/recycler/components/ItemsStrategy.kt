package com.elpassion.android.commons.recycler.components

interface ItemsStrategy<out T> {
    fun allItems(): List<T>
}

