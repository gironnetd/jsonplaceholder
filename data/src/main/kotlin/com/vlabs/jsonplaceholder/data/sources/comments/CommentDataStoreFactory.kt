package com.vlabs.jsonplaceholder.data.sources.comments
import com.vlabs.jsonplaceholder.data.repositories.comments.CommentCache
import com.vlabs.jsonplaceholder.data.repositories.comments.CommentDataStore
import com.vlabs.jsonplaceholder.data.repositories.posts.PostCache
import com.vlabs.jsonplaceholder.data.repositories.posts.PostDataStore
import javax.inject.Inject

/**
 * Create an instance of a TodoDataStore
 */
open class CommentDataStoreFactory @Inject constructor(
        private val commentCache: CommentCache,
        private val commentCacheDataStore: CommentCacheDataStore,
        private val commentRemoteDataStore: CommentRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(postId: Int): CommentDataStore {
        if (commentCache.isCached(postId) && !commentCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): CommentDataStore {
        return commentCacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): CommentDataStore {
        return commentRemoteDataStore
    }

}