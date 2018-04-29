package com.vlabs.jsonplaceholder.data.sources.comments

import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.repositories.comments.CommentCache
import com.vlabs.jsonplaceholder.data.repositories.comments.CommentDataStore
import com.vlabs.jsonplaceholder.data.repositories.posts.PostCache
import com.vlabs.jsonplaceholder.data.repositories.posts.PostDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [CommentDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class CommentCacheDataStore @Inject constructor(private val commentCache: CommentCache) :
        CommentDataStore {

    /**
     * Clear all Bufferoos from the cache
     */
    override fun clearComments(): Completable {
        return commentCache.clearComments()
    }

    /**
     * Save a given [List] of [CommentDataStore] instances to the cache
     */
    override fun saveComments(posts: List<CommentEntity>): Completable {
        return commentCache.saveComments(posts)
                .doOnComplete {
                    commentCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    override fun getCommentsByPostId(postId: Int): Single<List<CommentEntity>> {
        return commentCache.getCommentsByPostId(postId)
    }
}