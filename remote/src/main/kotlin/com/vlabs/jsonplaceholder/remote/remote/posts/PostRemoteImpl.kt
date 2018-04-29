package com.vlabs.jsonplaceholder.remote.remote.posts

import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.repositories.posts.PostRemote
import com.vlabs.jsonplaceholder.remote.mappers.posts.PostEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.users.UserEntityMapper
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [PostRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
@Singleton
class PostRemoteImpl @Inject constructor(private val postService: PostService,
                                         private val entityMapper: PostEntityMapper) :
        PostRemote {


    /**
     * Retrieve a list of [PostEntity] instances from the [PostService].
     */

    override fun getPosts(): Single<List<PostEntity>> {
        //val p = postService.getPosts()
        return postService.getPosts()
                .map {
                    //it.posts.map { listItem ->
                        entityMapper.mapFromRemote(it)
                    //}
                }
    }

    override fun getPostsByUserId(userId: Int): Single<List<PostEntity>> {
        return postService.getPostsByUserId(userId)
                .map {
                    //it.posts.map { listItem ->
                    entityMapper.mapFromRemote(it)
                    //}
                }
    }

}