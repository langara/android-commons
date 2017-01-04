package com.elpassion.android.commons.recycler.basic

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View


open class BasicViewHolder<V: View, in I>(itemView: V) : ViewHolder(itemView){
    open fun bind(item: I) {}
}