package com.vlabs.jsonplaceholder.ui.comments

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.model.CommentViewModel
import com.vlabs.jsonplaceholder.model.UserViewModel
import com.vlabs.jsonplaceholder.presentation.model.USER_ID_KEY
import com.vlabs.jsonplaceholder.ui.userdetails.UserDetailsActivity
import javax.inject.Inject

class CommentsListAdapter @Inject constructor(val context: Context): RecyclerView.Adapter<CommentsListAdapter.ViewHolder>() {

    var comments: List<CommentViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]
        holder.bodyText.text = comment.name
        holder.emailText.text = comment.email
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_comments_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //var avatarImage: ImageView
        var bodyText: TextView
        var emailText: TextView
        var position: Int? = 0

        init {
            //avatarImage = view.findViewById(R.id.image_avatar)
            bodyText = view.findViewById(R.id.text_name)
            emailText = view.findViewById(R.id.text_email)
//            view.setOnClickListener {
//                val intent = Intent(context, UserDetailsActivity::class.java)
//                intent.putExtra(USER_ID_KEY, position)
//                context.startActivity(intent)
//            }
        }
    }

}