package com.vlabs.jsonplaceholder.remote.remote.comments
import com.vlabs.jsonplaceholder.remote.remote.Service
import com.vlabs.jsonplaceholder.remote.remote.posts.PostService
import okhttp3.OkHttpClient

/**
 * Provide "make" methods to create instances of [UserService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object CommentServiceImpl : Service() {

    fun makeService(isDebug: Boolean): CommentService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor(isDebug))
        return makeService(okHttpClient)
    }

    private fun makeService(okHttpClient: OkHttpClient): CommentService {
        return initRetrofit(okHttpClient).create(CommentService::class.java)
    }
}