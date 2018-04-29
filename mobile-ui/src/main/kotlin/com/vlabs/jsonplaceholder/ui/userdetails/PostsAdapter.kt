package com.vlabs.jsonplaceholder.ui.userdetails

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.model.PostViewModel
import com.vlabs.jsonplaceholder.ui.comments.CommentsListActivity
import com.vlabs.jsonplaceholer.presentation.model.POST_ID_KEY
import javax.inject.Inject

class PostsAdapter @Inject constructor(val context: Context): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    var posts: List<PostViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.titleText.text = post.title
        holder.bodyText.text = post.body
        holder.position = post.id.toInt()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_posts_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var titleText: TextView
        var bodyText: TextView
        var position: Int? = -1

        init {
            titleText = view.findViewById(R.id.text_title)
            bodyText = view.findViewById(R.id.text_body)
            view.setOnClickListener {
                val intent = Intent(context, CommentsListActivity::class.java)
                intent.putExtra(POST_ID_KEY, position)

                context.startActivity(intent)
            }
        }
    }

}