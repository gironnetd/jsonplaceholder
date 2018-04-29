package com.vlabs.jsonplaceholder.data.sources.posts

import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.repositories.posts.PostCache
import com.vlabs.jsonplaceholder.data.repositories.posts.PostDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class PostCacheDataStore @Inject constructor(private val postCache: PostCache) :
        PostDataStore {


    override fun getPostsByUserId(userId: Int): Single<List<PostEntity>> {
        return postCache.getPostsByUserId(userId)
    }

    /**
     * Clear all Bufferoos from the cache
     */
    override fun clearPosts(): Completable {
        return postCache.clearPosts()
    }

    /**
     * Save a given [List] of [BufferooEntity] instances to the cache
     */
    override fun savePosts(posts: List<PostEntity>): Completable {
        return postCache.savePosts(posts)
                .doOnComplete {
                    postCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    /**
     * Retrieve a list of [BufferooEntity] instance from the cache
     */
    override fun getPosts(): Single<List<PostEntity>> {
        return postCache.getPosts()
    }

}