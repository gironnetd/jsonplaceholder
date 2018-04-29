package com.vlabs.jsonplaceholder.data.sources.posts

import com.vlabs.jsonplaceholder.data.repositories.posts.PostCache
import com.vlabs.jsonplaceholder.data.repositories.posts.PostDataStore
import javax.inject.Inject

/**
 * Create an instance of a TodoDataStore
 */
open class PostDataStoreFactory @Inject constructor(
        private val postCache: PostCache,
        private val postCacheDataStore: PostCacheDataStore,
        private val postRemoteDataStore: PostRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(userId: Int): PostDataStore {
        if (postCache.isCached(userId) && !postCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): PostDataStore {
        return postCacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): PostDataStore {
        return postRemoteDataStore
    }

}