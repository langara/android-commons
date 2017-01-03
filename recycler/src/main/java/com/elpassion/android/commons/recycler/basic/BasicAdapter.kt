package com.elpassion.android.commons.recycler.basic

import android.support.v7.widget.RecyclerView
import android.view.View


abstract class BasicAdapter<V : View, I>(val items: BasicList<I>) : RecyclerView.Adapter<BasicViewHolder<V, I>>() {

    override fun onBindViewHolder(holder: BasicViewHolder<V, I>, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}