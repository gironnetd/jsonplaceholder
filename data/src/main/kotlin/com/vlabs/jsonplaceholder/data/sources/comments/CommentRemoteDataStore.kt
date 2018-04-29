package com.vlabs.jsonplaceholder.data.sources.comments

import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.data.repositories.comments.CommentDataStore
import com.vlabs.jsonplaceholder.data.repositories.comments.CommentRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class CommentRemoteDataStore @Inject constructor(private val commentRemote: CommentRemote) :
        CommentDataStore {

    override fun clearComments(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveComments(posts: List<CommentEntity>): Completable {
        throw UnsupportedOperationException()
    }

    override fun getCommentsByPostId(postId: Int): Single<List<CommentEntity>> {
        return commentRemote.getCommentsByPostId(postId)
    }
}