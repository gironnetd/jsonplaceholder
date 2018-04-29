package com.vlabs.jsonplaceholder.remote.remote.comments

import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.repositories.comments.CommentRemote
import com.vlabs.jsonplaceholder.data.repositories.posts.PostRemote
import com.vlabs.jsonplaceholder.remote.mappers.comments.CommentEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.posts.PostEntityMapper
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [PostRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
@Singleton
class CommentRemoteImpl @Inject constructor(private val commentService: CommentService,
                                            private val entityMapper: CommentEntityMapper) :
        CommentRemote {

    override fun getCommentsByPostId(postId: Int): Single<List<CommentEntity>> {
        return commentService.getCommentsByPostId(postId)
                .map {
                    //it.posts.map { listItem ->
                    entityMapper.mapFromRemote(it)
                    //}
                }
    }


    /**
     * Retrieve a list of [PostEntity] instances from the [CommentService].
     */

//    override fun getPosts(): Single<List<PostEntity>> {
//        //val p = commentService.getPosts()
//        return commentService.getPosts()
//                .map {
//                    //it.posts.map { listItem ->
//                        entityMapper.mapFromRemote(it)
//                    //}
//                }
//    }

}