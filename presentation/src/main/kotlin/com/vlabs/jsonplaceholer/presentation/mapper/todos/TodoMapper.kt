package com.vlabs.jsonplaceholer.presentation.mapper.todos

import com.vlabs.jsonplaceholder.domain.models.Todo
import com.vlabs.jsonplaceholder.presentation.mapper.Mapper
import com.vlabs.jsonplaceholer.presentation.model.TodoView
import javax.inject.Inject

/**
 * Map a [TodoView] to and from a [Todo] instance when data is moving between
 * this layer and the Domain layer
 */
open class TodoMapper @Inject constructor(): Mapper<TodoView, Todo> {

    /**
     * Map a [Todo] instance to a [TodoView] instance
     */
    override fun mapToView(type: Todo): TodoView {
        return TodoView(type.userId, type.id, type.title,type.completed.toBoolean())
    }
}