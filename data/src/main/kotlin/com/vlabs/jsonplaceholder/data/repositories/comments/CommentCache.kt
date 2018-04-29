package com.vlabs.jsonplaceholder.data.repositories.comments

import com.vlabs.jsonplaceholder.data.models.BufferooEntity
import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface CommentCache {

    /**
     * Clear all Bufferoos from the cache
     */
    fun clearComments(): Completable

    /**
     * Save a given list of Bufferoos to the cache
     */
    fun saveComments(posts: List<CommentEntity>): Completable

    /**
     * Retrieve a list of Bufferoos, from the cache
     */
    fun getComments(): Single<List<CommentEntity>>

    fun getCommentsByPostId(postId: Int): Single<List<CommentEntity>>

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(postId: Int): Boolean

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