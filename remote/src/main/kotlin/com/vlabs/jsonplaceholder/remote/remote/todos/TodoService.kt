package com.vlabs.jsonplaceholder.remote.remote.todos

import com.vlabs.jsonplaceholder.remote.models.PostModel
import com.vlabs.jsonplaceholder.remote.models.TodoModel
import com.vlabs.jsonplaceholder.remote.models.UserModel
import com.vlabs.jsonplaceholder.remote.remote.Service
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface TodoService {
    //https://jsonplaceholder.typicode.com/users/1/posts
    @GET("todos")
    fun getTodos(): Single<List<TodoModel>>

    @GET("users/{id}/todos")
    fun getTodosByUserId(@Path("id") userId: Int): Single<List<TodoModel>>
}
