package com.elpassion.android.commons.recycler_example.group

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.elpassion.android.commons.recycler.adapters.basicAdapterWithCreator
import com.elpassion.android.commons.recycler.basic.addAll
import com.elpassion.android.commons.recycler.basic.asBasicListWithMutableSections
import com.elpassion.android.commons.recycler.basic.asBasicMutableList
import com.elpassion.android.commons.recycler_example.R
import com.elpassion.android.commons.recycler_example.common.*
import com.elpassion.android.view.inflate
import kotlinx.android.synthetic.main.recycler_view_with_action.*
import java.util.*

class BasicMutableRecyclerWithSectionsActivity : AppCompatActivity() {

    val users = createManyUsers()
            .groupByTo(LinkedHashMap(), User::organization)
            .mapValuesTo(LinkedHashMap()) { it.value.asBasicMutableList() }
            .asBasicListWithMutableSections()

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_with_action)
        recyclerView.layoutManager = android.support.v7.widget.LinearLayoutManager(this)
        val adapter = basicAdapterWithCreator<View, User>(users) { position ->
            if (users[position].organization == "A")
                R.layout.github_item to { parent -> SimpleUserViewHolder(parent.inflate(R.layout.github_item)) }
            else
                R.layout.other_github_item to { parent -> OtherSimpleUserViewHolder(parent.inflate(R.layout.other_github_item)) }
        }
        adapter.setHasStableIds(true)
        recyclerView.adapter = adapter

        clearSectionButton.setOnClickListener {
            users.sections["A"]!!.clear()
            adapter.notifyDataSetChanged()
            restoreSectionButton.isEnabled = true
            clearSectionButton.isEnabled = false
        }
        restoreSectionButton.setOnClickListener {
            users.sections["A"]!!.addAll(createUsersWithASection())
            adapter.notifyDataSetChanged()
            restoreSectionButton.isEnabled = false
            clearSectionButton.isEnabled = true
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, BasicMutableRecyclerWithSectionsActivity::class.java))
        }

        const val DESCRIPTION = "Basic mutable recycler with sections"
    }
}
