package com.vlabs.jsonplaceholder.remote.remote.comments

import com.vlabs.jsonplaceholder.remote.models.CommentModel
import com.vlabs.jsonplaceholder.remote.models.PostModel
import com.vlabs.jsonplaceholder.remote.models.UserModel
import com.vlabs.jsonplaceholder.remote.remote.Service
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface CommentService {
    //https://jsonplaceholder.typicode.com/users/1/posts
    @GET("posts/{id}/comments")
    fun getCommentsByPostId(@Path("id") postId: Int): Single<List<CommentModel>>
}
