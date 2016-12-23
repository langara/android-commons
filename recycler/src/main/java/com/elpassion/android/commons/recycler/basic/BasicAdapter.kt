package com.elpassion.android.commons.recycler.basic

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import java.util.*


class BasicAdapter<V : View, I>(
        val items: BasicList<I>,
        val getTypeAndCreator: (position: Int) -> Pair<Int, (parent: ViewGroup) -> ViewBinder<V, I>>
)
    : RecyclerView.Adapter<ViewBinder<V, I>>() {

    private val creators = HashMap<Int, (parent: ViewGroup) -> ViewBinder<V, I>>()

    override fun getItemViewType(position: Int): Int {
        val (type, creator) = getTypeAndCreator(position)
        creators[type] = creator
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = creators[viewType]!!(parent)

    override fun onBindViewHolder(binder: ViewBinder<V, I>, position: Int) = binder.bind(items[position])

    override fun getItemCount() = items.size

}