package com.vlabs.jsonplaceholder.domain.repositories.posts

import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.models.Post
import io.reactivex.Completable
import io.reactivex.Single

interface PostRepository {

    fun clearPosts(): Completable

    fun savePosts(users: List<Post>): Completable

    //fun getPosts(): Single<List<Post>>

    fun getPostsByUserId(userId: Int) : Single<List<Post>>
}