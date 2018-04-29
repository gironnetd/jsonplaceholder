package com.vlabs.jsonplaceholder.remote

import com.vlabs.jsonplaceholder.remote.remote.Service
import com.vlabs.jsonplaceholder.remote.remote.posts.PostService
import com.vlabs.jsonplaceholder.remote.remote.users.UserService
import okhttp3.OkHttpClient

/**
 * Provide "make" methods to create instances of [UserService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object UserServiceImpl : Service() {

    fun makeService(isDebug: Boolean): UserService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor(isDebug))
        return makeService(okHttpClient)
    }

    private fun makeService(okHttpClient: OkHttpClient): UserService {
        return initRetrofit(okHttpClient).create(UserService::class.java)
    }
}