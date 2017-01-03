package com.elpassion.android.commons.recycler_example.group

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.elpassion.android.commons.recycler.adapters.basicAdapterWithLayout
import com.elpassion.android.commons.recycler.basic.asBasicListWithSections
import com.elpassion.android.commons.recycler_example.R
import com.elpassion.android.commons.recycler_example.common.User
import com.elpassion.android.commons.recycler_example.common.createManyUsers
import kotlinx.android.synthetic.main.recycler_view.*

class BasicRecyclerWithSectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val users = createManyUsers().groupBy(User::organization).asBasicListWithSections()
        recyclerView.adapter = basicAdapterWithLayout(users, R.layout.github_item)

        Log.i(BasicRecyclerWithSectionActivity::class.java.name, users.sections["A"]!!.size.toString())
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, BasicRecyclerWithSectionActivity::class.java))
        }

        const val DESCRIPTION = "Basic recycler with sections"
    }
}