package com.vlabs.jsonplaceholder.remote.remote.photos

import com.vlabs.jsonplaceholder.remote.remote.Service
import com.vlabs.jsonplaceholder.remote.remote.posts.PostService
import okhttp3.OkHttpClient

/**
 * Provide "make" methods to create instances of [UserService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object PhotoServiceImpl : Service() {

    fun makeService(isDebug: Boolean): PhotoService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor(isDebug))
        return makeService(okHttpClient)
    }

    private fun makeService(okHttpClient: OkHttpClient): PhotoService {
        return initRetrofit(okHttpClient).create(PhotoService::class.java)
    }
}