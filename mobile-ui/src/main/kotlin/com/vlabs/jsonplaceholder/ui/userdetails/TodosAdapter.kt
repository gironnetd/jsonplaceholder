package com.vlabs.jsonplaceholder.ui.userdetails

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vlabs.jsonplaceholder.R
import com.vlabs.jsonplaceholder.model.TodoViewModel
import javax.inject.Inject

class TodosAdapter @Inject constructor(val context: Context): RecyclerView.Adapter<TodosAdapter.ViewHolder>() {

    var todos: List<TodoViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todos[position]
        holder.titleText.text = todo.title
        if(todo.completed) {
            holder.completedText.text = "completed"
            holder.completedText.setTextColor(Color.GREEN)
        } else {
            holder.completedText.text = "uncomplete"
            holder.completedText.setTextColor(Color.RED)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_todos_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var titleText: TextView
        var completedText: TextView

        init {
            titleText = view.findViewById(R.id.text_title)
            completedText = view.findViewById(R.id.text_completed)
        }
    }

}