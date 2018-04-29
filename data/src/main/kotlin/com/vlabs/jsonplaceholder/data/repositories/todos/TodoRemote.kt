package com.vlabs.jsonplaceholder.data.repositories.todos

import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.models.TodoEntity
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface TodoRemote {

    /**
     * Retrieve a list of Bufferoos, from the cache
     */
    fun getTodosByUserId(userId: Int): Single<List<TodoEntity>>

}