package com.vlabs.jsonplaceholder.remote.remote.todos

import com.vlabs.jsonplaceholder.remote.remote.Service
import com.vlabs.jsonplaceholder.remote.remote.posts.PostService
import okhttp3.OkHttpClient

/**
 * Provide "make" methods to create instances of [UserService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object TodoServiceImpl : Service() {

    fun makeService(isDebug: Boolean): TodoService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor(isDebug))
        return makeService(okHttpClient)
    }

    private fun makeService(okHttpClient: OkHttpClient): TodoService {
        return initRetrofit(okHttpClient).create(TodoService::class.java)
    }
}