package com.vlabs.jsonplaceholder.data.sources.comments

import com.vlabs.jsonplaceholder.data.mappers.comments.CommentMapper
import com.vlabs.jsonplaceholder.data.mappers.posts.PostMapper
import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholder.domain.models.Post
import com.vlabs.jsonplaceholder.domain.repositories.comments.CommentRepository
import com.vlabs.jsonplaceholder.domain.repositories.posts.PostRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [BufferooRepository] interface for communicating to and from
 * data sources
 */
class CommentDataRepository @Inject constructor(private val factory: CommentDataStoreFactory,
                                                private val postMapper: CommentMapper) :
        CommentRepository {



//    override fun clearComments(): Completable {
//        return factory.retrieveCacheDataStore().clearComments()
//    }
//
//    override fun saveComments(posts: List<Comment>): Completable {
//        val postEntities = posts.map { postMapper.mapToEntity(it) }
//        return saveCommentEntities(postEntities)
//    }

    private fun saveCommentEntities(posts: List<CommentEntity>): Completable {
        return factory.retrieveCacheDataStore().saveComments(posts)
    }

    override fun getCommentsByPostId(postId: Int): Single<List<Comment>> {
        val dataStore = factory.retrieveDataStore(postId)
        return dataStore.getCommentsByPostId(postId)
                .flatMap {
                    if (dataStore is CommentRemoteDataStore) {
                        saveCommentEntities(it).toSingle { it }
                    } else {
                        Single.just(it)
                    }
                }
                .map { list ->
                    list.map { listItem ->
                        postMapper.mapFromEntity(listItem)
                    }
                }
    }

}