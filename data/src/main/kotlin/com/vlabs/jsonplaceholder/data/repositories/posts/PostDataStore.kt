package com.vlabs.jsonplaceholder.data.repositories.posts

import com.vlabs.jsonplaceholder.data.models.PostEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to Bufferroos.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface PostDataStore {

    fun clearPosts(): Completable

    fun savePosts(posts: List<PostEntity>): Completable

    fun getPosts(): Single<List<PostEntity>>

    fun getPostsByUserId(userId: Int): Single<List<PostEntity>>

}