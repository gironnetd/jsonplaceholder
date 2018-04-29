package com.vlabs.jsonplaceholder.remote.mappers.todos

import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.models.TodoEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.remote.mappers.EntityMapper
import com.vlabs.jsonplaceholder.remote.models.PostModel
import com.vlabs.jsonplaceholder.remote.models.TodoModel
import com.vlabs.jsonplaceholder.remote.models.UserModel
import javax.inject.Inject

/**
 * Map a [PostModel] to and from a [PostEntity] instance when data is moving between
 * this later and the Data layer
 */
open class TodoEntityMapper @Inject constructor(): EntityMapper<TodoModel, TodoEntity> {

    /**
     * Map an instance of a [TodoModel] to a [TodoEntity] model
     */
    override fun mapFromRemote(type: TodoModel): TodoEntity {
        return  TodoEntity(type.userId, type.id, type.title, type.completed.toString())
    }

    override fun mapFromRemote(type: List<TodoModel>): List<TodoEntity> {
        return type.map { mapFromRemote(it) }
    }
}