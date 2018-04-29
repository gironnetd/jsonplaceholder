package com.vlabs.jsonplaceholder.ui.photos

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.model.PhotoViewModel
import com.vlabs.jsonplaceholder.model.UserViewModel
import com.vlabs.jsonplaceholder.presentation.model.USER_ID_KEY
import com.vlabs.jsonplaceholder.ui.userdetails.UserDetailsActivity
import javax.inject.Inject

class PhotosListAdapter @Inject constructor(val context: Context): RecyclerView.Adapter<PhotosListAdapter.ViewHolder>() {

    var photos: List<PhotoViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        holder.titleText.text = photo.title

        Glide.with(holder.itemView.context)
                .load(photo.thumbnailUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.photoImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_photos_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var photoImage: ImageView
        var titleText: TextView
        var position: Int? = 0

        init {
            photoImage = view.findViewById(R.id.image_photo)
            titleText = view.findViewById(R.id.text_title)

        }
    }

}