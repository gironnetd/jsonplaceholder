package com.vlabs.jsonplaceholder.ui.users

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.model.UserViewModel
import com.vlabs.jsonplaceholder.presentation.model.USER_ID_KEY
import com.vlabs.jsonplaceholder.ui.userdetails.UserDetailsActivity
import javax.inject.Inject

class UsersListAdapter @Inject constructor(val context: Context): RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    var users: List<UserViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.nameText.text = user.name
        holder.userNameText.text = user.userName
        holder.emailText.text = user.email
        holder.position = user.userId
        //holder.titleText.text = bufferoo.title

//        Glide.with(holder.itemView.context)
//                .load(bufferoo.avatar)
//                .apply(RequestOptions.circleCropTransform())
//                .into(holder.avatarImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_users_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //var avatarImage: ImageView
        var nameText: TextView
        var userNameText: TextView
        var emailText: TextView
        var position: Int? = 0

        init {
            //avatarImage = view.findViewById(R.id.image_avatar)
            nameText = view.findViewById(R.id.text_name)
            userNameText = view.findViewById(R.id.text_username)
            emailText = view.findViewById(R.id.text_email)
            view.setOnClickListener {
                val intent = Intent(context, UserDetailsActivity::class.java)
                intent.putExtra(USER_ID_KEY, position)
                context.startActivity(intent)
            }
        }
    }

}