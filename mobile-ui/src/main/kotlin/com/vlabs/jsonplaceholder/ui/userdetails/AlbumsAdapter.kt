package com.vlabs.jsonplaceholder.ui.userdetails

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.model.AlbumViewModel
import com.vlabs.jsonplaceholder.ui.photos.PhotosListActivity
import com.vlabs.jsonplaceholer.presentation.model.ALBUM_ID_KEY
import javax.inject.Inject

class AlbumsAdapter @Inject constructor(val context: Context): RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    var albums: List<AlbumViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albums[position]
        holder.nameText.text = album.title
        holder.position = album.id


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_albums_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var nameText: TextView
        var position: Int? = -1

        init {
            nameText = view.findViewById(R.id.text_name)
            view.setOnClickListener {
                val intent = Intent(context, PhotosListActivity::class.java)
                intent.putExtra(ALBUM_ID_KEY, position)
                context.startActivity(intent)
            }
        }
    }

}