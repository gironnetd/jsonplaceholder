package com.vlabs.jsonplaceholder.data.repositories.comments

import com.vlabs.jsonplaceholder.data.models.CommentEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to Bufferroos.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface CommentDataStore {

    fun clearComments(): Completable

    fun saveComments(posts: List<CommentEntity>): Completable

    fun getCommentsByPostId(postId: Int): Single<List<CommentEntity>>

}