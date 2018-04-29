package com.vlabs.jsonplaceholder.data.repositories.todos

import com.vlabs.jsonplaceholder.data.models.BufferooEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.models.TodoEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface TodoCache {

    /**
     * Clear all Bufferoos from the cache
     */
    fun clearTodos(): Completable

    /**
     * Save a given list of Bufferoos to the cache
     */
    fun saveTodos(posts: List<TodoEntity>): Completable

    /**
     * Retrieve a list of Bufferoos, from the cache
     */
    fun getTodos(): Single<List<TodoEntity>>

    fun getTodosByUserId(userId: Int): Single<List<TodoEntity>>

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(userId: Int): Boolean

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Checks if the cache is expired.

     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(): Boolean

}