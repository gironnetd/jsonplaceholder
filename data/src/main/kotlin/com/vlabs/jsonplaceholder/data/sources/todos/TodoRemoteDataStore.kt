package com.vlabs.jsonplaceholder.data.sources.todos

import com.vlabs.jsonplaceholder.data.models.TodoEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.data.repositories.todos.TodoDataStore
import com.vlabs.jsonplaceholder.data.repositories.todos.TodoRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class TodoRemoteDataStore @Inject constructor(private val todoRemote: TodoRemote) :
        TodoDataStore {


    override fun clearTodos(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveTodos(posts: List<TodoEntity>): Completable {
        throw UnsupportedOperationException()
    }

    override fun getTodosByUserId(userId: Int): Single<List<TodoEntity>> {
        return todoRemote.getTodosByUserId(userId)
    }

}