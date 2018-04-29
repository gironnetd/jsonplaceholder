package com.vlabs.jsonplaceholder.remote.remote.posts

import com.vlabs.jsonplaceholder.remote.models.PostModel
import com.vlabs.jsonplaceholder.remote.models.UserModel
import com.vlabs.jsonplaceholder.remote.remote.Service
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface PostService {
    //https://jsonplaceholder.typicode.com/users/1/posts
    @GET("posts")
    fun getPosts(): Single<List<PostModel>>

    @GET("users/{id}/posts")
    fun getPostsByUserId(@Path("id") userId: Int): Single<List<PostModel>>
}
