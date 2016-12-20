package com.elpassion.android.commons.recycler.basic

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup


class BasicAdapter<V : View, I>(
        val items: BasicList<I>,
        val getType: (position: Int) -> Int = { 0 },
        val create: (viewParent: ViewGroup, viewType: Int) -> ViewBinder<V, I>
)
    : RecyclerView.Adapter<ViewBinder<V, I>>() {

    override fun onBindViewHolder(binder: ViewBinder<V, I>, position: Int) = binder.bind(items[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = create(parent, viewType)

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = getType(position)
}