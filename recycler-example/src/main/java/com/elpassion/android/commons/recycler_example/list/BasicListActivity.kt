package com.elpassion.android.commons.recycler_example.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.elpassion.android.commons.recycler.basic.BasicAdapter
import com.elpassion.android.commons.recycler.basic.ViewBinder
import com.elpassion.android.commons.recycler.basic.asBasicList
import com.elpassion.android.commons.recycler_example.R
import com.elpassion.android.commons.recycler_example.common.User
import com.elpassion.android.commons.recycler_example.common.createManyUsers
import com.elpassion.android.view.inflate
import kotlinx.android.synthetic.main.github_item.view.*
import kotlinx.android.synthetic.main.recycler_view.*

class BasicListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)
        val users = createManyUsers().asBasicList()
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = BasicAdapter.create<View, User>(users) { position ->
             if(users[position].organization == "A")
                 R.layout.github_item to { parent -> SimpleUserBinder(parent.inflate(R.layout.github_item))}
             else
                 R.layout.other_github_item to { parent -> OtherUserBinder(parent.inflate(R.layout.other_github_item))}
        }
    }

    class SimpleUserBinder(itemView: View) : ViewBinder<View, User>(itemView) {
        override fun bind(item: User) {
            itemView.userName.text = item.userName
            itemView.organization.text = item.organization
        }
    }

    class OtherUserBinder(itemView: View) : ViewBinder<View, User>(itemView) {
        override fun bind(item: User) {
            itemView.userName.text = item.userName.reversed()
            itemView.organization.text = item.organization
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, BasicListActivity::class.java))
        }

        const val DESCRIPTION = "Basic recycler with different item views"
    }
}
