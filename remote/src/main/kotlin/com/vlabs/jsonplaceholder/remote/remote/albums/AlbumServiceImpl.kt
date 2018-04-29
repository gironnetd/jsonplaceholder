package com.vlabs.jsonplaceholder.remote.remote.albums

import com.vlabs.jsonplaceholder.remote.remote.Service
import com.vlabs.jsonplaceholder.remote.remote.posts.PostService
import okhttp3.OkHttpClient

/**
 * Provide "make" methods to create instances of [UserService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object AlbumServiceImpl : Service() {

    fun makeService(isDebug: Boolean): AlbumService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor(isDebug))
        return makeService(okHttpClient)
    }

    private fun makeService(okHttpClient: OkHttpClient): AlbumService {
        return initRetrofit(okHttpClient).create(AlbumService::class.java)
    }
}