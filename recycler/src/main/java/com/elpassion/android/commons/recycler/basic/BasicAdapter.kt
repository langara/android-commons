package com.elpassion.android.commons.recycler.basic

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.NO_ID
import android.view.View


abstract class BasicAdapter<V : View, I>(var items: BasicList<I>) : RecyclerView.Adapter<BasicViewHolder<V, I>>() {

    override fun onBindViewHolder(holder: BasicViewHolder<V, I>, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    override fun getItemId(position: Int) = (items[position] as? WithStableId)?.id ?: NO_ID
}