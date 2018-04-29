package com.vlabs.jsonplaceholder.data.sources.todos

import com.vlabs.jsonplaceholder.data.repositories.posts.PostCache
import com.vlabs.jsonplaceholder.data.repositories.posts.PostDataStore
import com.vlabs.jsonplaceholder.data.repositories.todos.TodoCache
import com.vlabs.jsonplaceholder.data.repositories.todos.TodoDataStore
import javax.inject.Inject

/**
 * Create an instance of a TodoDataStore
 */
open class TodoDataStoreFactory @Inject constructor(
        private val todoCache: TodoCache,
        private val todoCacheDataStore: TodoCacheDataStore,
        private val todoRemoteDataStore: TodoRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(userId: Int): TodoDataStore {
        if (todoCache.isCached(userId) && !todoCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): TodoDataStore {
        return todoCacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): TodoDataStore {
        return todoRemoteDataStore
    }

}