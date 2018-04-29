package com.vlabs.jsonplaceholder.data.mappers.todos

import com.vlabs.jsonplaceholder.cache.model.todos.CachedTodo
import com.vlabs.jsonplaceholder.data.models.TodoEntity
import org.buffer.android.boilerplate.cache.mapper.EntityMapper
import javax.inject.Inject

class TodoEntityMapper @Inject constructor(): EntityMapper<CachedTodo, TodoEntity> {

    /**
     * Map a [BufferooEntity] instance to a [CachedBufferoo] instance
     */
    override fun mapToCached(type: TodoEntity): CachedTodo {
        return CachedTodo(type.userId, type.id, type.title, type.completed.toString())
    }

    /**
     * Map a [CachedBufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapFromCached(type: CachedTodo): TodoEntity {
        return TodoEntity(type.userId, type.id, type.title, type.completed)

    }

}