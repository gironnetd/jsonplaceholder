package com.vlabs.jsonplaceholder.data.sources.posts

import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.data.repositories.posts.PostDataStore
import com.vlabs.jsonplaceholder.data.repositories.posts.PostRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class PostRemoteDataStore @Inject constructor(private val postRemote: PostRemote) :
        PostDataStore {

    override fun getPostsByUserId(userId: Int): Single<List<PostEntity>> {
        return postRemote.getPostsByUserId(userId)
    }

    override fun clearPosts(): Completable {
        throw UnsupportedOperationException()
    }

    override fun savePosts(posts: List<PostEntity>): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [BufferooEntity] instances from the API
     */
    override fun getPosts(): Single<List<PostEntity>> {
        return postRemote.getPosts()
    }

}