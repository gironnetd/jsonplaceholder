package com.vlabs.jsonplaceholder.mappers

import com.vlabs.jsonplaceholder.model.TodoViewModel
import com.vlabs.jsonplaceholer.presentation.model.TodoView
import javax.inject.Inject

open class TodoMapper @Inject constructor(): Mapper<TodoViewModel, TodoView> {

    /**
     * Map a [TodoView] instance to a [PostViewModel] instance
     */
    override fun mapToViewModel(type: TodoView): TodoViewModel {
        return TodoViewModel(type.userId, type.id, type.title, type.completed)
    }
}