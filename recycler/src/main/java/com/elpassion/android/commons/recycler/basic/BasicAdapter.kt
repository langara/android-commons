package com.elpassion.android.commons.recycler.basic

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup


abstract class BasicAdapter<V : View, I>(val items: BasicList<I>) : RecyclerView.Adapter<BasicViewHolder<V, I>>() {

    override fun getItemViewType(position: Int) = 0

    override abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicViewHolder<V, I>

    override fun onBindViewHolder(holder: BasicViewHolder<V, I>, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}