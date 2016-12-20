package com.elpassion.android.commons.recycler_example.menu

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.elpassion.android.commons.recycler.adapters.recyclerViewAdapter
import com.elpassion.android.commons.recycler_example.R
import com.elpassion.android.commons.recycler_example.group.MutableRecyclerWithSectionsActivity
import com.elpassion.android.commons.recycler_example.group.RecyclerWithSectionActivity
import com.elpassion.android.commons.recycler_example.group.StableMutableRecyclerWithSectionsActivity
import com.elpassion.android.commons.recycler_example.list.BasicListActivity
import com.elpassion.android.commons.recycler_example.list.SimpleListActivity
import kotlinx.android.synthetic.main.recycler_view.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val examples = listOf(
                ExampleItem(name = SimpleListActivity.DESCRIPTION, onClick = { SimpleListActivity.start(this) }),
                ExampleItem(name = BasicListActivity.DESCRIPTION, onClick = { BasicListActivity.start(this) }),
                ExampleItem(name = RecyclerWithSectionActivity.DESCRIPTION, onClick = { RecyclerWithSectionActivity.start(this) }),
                ExampleItem(name = MutableRecyclerWithSectionsActivity.DESCRIPTION, onClick = { MutableRecyclerWithSectionsActivity.start(this) }),
                ExampleItem(name = StableMutableRecyclerWithSectionsActivity.DESCRIPTION, onClick = { StableMutableRecyclerWithSectionsActivity.start(this) })
        )
        recyclerView.adapter = recyclerViewAdapter(examples.map(::ExampleItemAdapter))
    }
}