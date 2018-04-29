package com.vlabs.jsonplaceholder.remote

import com.vlabs.jsonplaceholder.remote.remote.Service
import com.vlabs.jsonplaceholder.remote.remote.posts.PostService
import okhttp3.OkHttpClient

/**
 * Provide "make" methods to create instances of [UserService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object PostServiceImpl : Service() {

    fun makeService(isDebug: Boolean): PostService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor(isDebug))
        return makeService(okHttpClient)
    }

    private fun makeService(okHttpClient: OkHttpClient): PostService {
        return initRetrofit(okHttpClient).create(PostService::class.java)
    }
}