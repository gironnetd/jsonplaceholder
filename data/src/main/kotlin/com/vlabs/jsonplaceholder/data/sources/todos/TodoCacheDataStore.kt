package com.vlabs.jsonplaceholder.data.sources.todos

import com.vlabs.jsonplaceholder.data.models.TodoEntity
import com.vlabs.jsonplaceholder.data.repositories.todos.TodoCache
import com.vlabs.jsonplaceholder.data.repositories.todos.TodoDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class TodoCacheDataStore @Inject constructor(private val todoCache: TodoCache) :
        TodoDataStore {



    /**
     * Clear all Bufferoos from the cache
     */
    override fun clearTodos(): Completable {
        return todoCache.clearTodos()
    }

    /**
     * Save a given [List] of [BufferooEntity] instances to the cache
     */
    override fun saveTodos(posts: List<TodoEntity>): Completable {
        return todoCache.saveTodos(posts)
                .doOnComplete {
                    todoCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    /**
     * Retrieve a list of [BufferooEntity] instance from the cache
     */
//    override fun getTodos(): Single<List<TodoEntity>> {
//        return todoCache.getTodos()
//    }

    override fun getTodosByUserId(userId: Int): Single<List<TodoEntity>> {
        return todoCache.getTodosByUserId(userId)
    }

}