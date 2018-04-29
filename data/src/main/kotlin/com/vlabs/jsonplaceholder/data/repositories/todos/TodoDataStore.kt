package com.vlabs.jsonplaceholder.data.repositories.todos

import com.vlabs.jsonplaceholder.data.models.BufferooEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.models.TodoEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to Bufferroos.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface TodoDataStore {

    fun clearTodos(): Completable

    fun saveTodos(posts: List<TodoEntity>): Completable

    fun getTodosByUserId(userId: Int): Single<List<TodoEntity>>

}