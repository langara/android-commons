package com.elpassion.android.commons.recycler.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.elpassion.android.commons.recycler.RecyclerViewCompositeAdapter
import com.elpassion.android.commons.recycler.basic.BasicAdapter
import com.elpassion.android.commons.recycler.basic.BasicList
import com.elpassion.android.commons.recycler.basic.BasicViewHolder
import com.elpassion.android.commons.recycler.components.ItemsStrategy
import com.elpassion.android.commons.recycler.components.base.ItemAdapter
import com.elpassion.android.commons.recycler.components.base.ListItemsStrategy
import com.elpassion.android.commons.recycler.components.base.MutableListItemsStrategy
import com.elpassion.android.commons.recycler.components.group.SectionedItemsStrategy
import com.elpassion.android.commons.recycler.components.stable.StableItemAdapter
import com.elpassion.android.commons.recycler.components.stable.createStableIdInitialization
import com.elpassion.android.commons.recycler.components.stable.getStableItemIdentifier
import com.elpassion.android.view.inflate
import java.util.*

fun recyclerViewAdapter(adapters: List<ItemAdapter<*>>) = RecyclerViewCompositeAdapter(ListItemsStrategy(adapters))

fun mutableRecyclerViewAdapter(adapters: MutableList<ItemAdapter<*>> = mutableListOf()) = RecyclerViewCompositeAdapter(MutableListItemsStrategy(adapters))

fun stableRecyclerViewAdapter(itemsStrategy: ItemsStrategy<StableItemAdapter<out RecyclerView.ViewHolder>>) =
        RecyclerViewCompositeAdapter(
                itemsStrategy = itemsStrategy,
                getItemIdentifier = getStableItemIdentifier(itemsStrategy),
                init = createStableIdInitialization())

fun <Section, Item : StableItemAdapter<out RecyclerView.ViewHolder>> stableSectionedRecyclerViewAdapter(itemsStrategy: SectionedItemsStrategy<Section, Item>) =
        RecyclerViewCompositeAdapter(
                itemsStrategy = itemsStrategy,
                getItemIdentifier = getStableItemIdentifier(itemsStrategy),
                init = createStableIdInitialization())

fun <V : View, I> basicAdapterWithHolder(items: BasicList<I>, createHolder: (parent: ViewGroup) -> BasicViewHolder<V, I>) =
        object : BasicAdapter<V, I>(items) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = createHolder(parent)
        }

fun <I> basicAdapterWithLayoutAndBinder(items: BasicList<I>, layout: Int, binder: (holder: BasicViewHolder<View, I>, item: I) -> Unit) =
        basicAdapterWithHolder(items) { parent ->
            object : BasicViewHolder<View, I>(parent.inflate(layout)) {
                override fun bind(item: I) = binder(this, item)
            }
        }

fun <V : View, I> basicAdapterWithCreator(items: BasicList<I>, getTypeAndCreator: (position: Int) -> Pair<Int, (parent: ViewGroup) -> BasicViewHolder<V, I>>) =
        object : BasicAdapter<V, I>(items) {
            private val creators = HashMap<Int, (parent: ViewGroup) -> BasicViewHolder<V, I>>()
            override fun getItemViewType(position: Int): Int {
                val (type, creator) = getTypeAndCreator(position)
                creators[type] = creator
                return type
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = creators[viewType]!!(parent)
        }

fun <I> basicAdapterWithConstructors(items: BasicList<I>, getLayoutAndConstructor: (position: Int) -> Pair<Int, (itemView: View) -> BasicViewHolder<View, I>>) =
        basicAdapterWithCreator(items) { position ->
            val (layout, constructor) = getLayoutAndConstructor(position)
            layout to { parent: ViewGroup -> constructor(parent.inflate(layout)) }
        }
