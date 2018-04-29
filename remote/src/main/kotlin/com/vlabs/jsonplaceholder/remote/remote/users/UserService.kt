package com.vlabs.jsonplaceholder.remote.remote.users

import com.vlabs.jsonplaceholder.remote.models.UserModel
import com.vlabs.jsonplaceholder.remote.remote.Service
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface UserService {

    @GET("users")
    fun getUsers(): Single<List<UserModel>>
}
