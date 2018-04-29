package com.vlabs.jsonplaceholder.data.repositories.comments

import com.vlabs.jsonplaceholder.data.models.CommentEntity
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface CommentRemote {

    /**
     * Retrieve a list of Bufferoos, from the cache
     */
    fun getCommentsByPostId(postId: Int): Single<List<CommentEntity>>
}