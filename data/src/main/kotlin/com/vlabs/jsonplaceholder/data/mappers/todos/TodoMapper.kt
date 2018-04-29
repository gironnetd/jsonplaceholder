package com.vlabs.jsonplaceholder.data.mappers.todos

import com.vlabs.jsonplaceholder.data.mappers.Mapper
import com.vlabs.jsonplaceholder.data.models.TodoEntity
import com.vlabs.jsonplaceholder.domain.models.Todo
import javax.inject.Inject

open class TodoMapper @Inject constructor(): Mapper<TodoEntity, Todo> {

    /**
     * Map a [BufferooEntity] instance to a [Bufferoo] instance
     */
    override fun mapFromEntity(type: TodoEntity): Todo {
        return Todo(type.userId, type.id, type.title, type.completed)
    }

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: Todo): TodoEntity {
        return TodoEntity(type.userId, type.id, type.title, type.completed)
    }
}