package com.vlabs.jsonplaceholder.remote.remote.albums

import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.models.PostModel
import com.vlabs.jsonplaceholder.remote.models.UserModel
import com.vlabs.jsonplaceholder.remote.remote.Service
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface AlbumService {
    //https://jsonplaceholder.typicode.com/users/1/posts

    @GET("users/{id}/albums")
    fun getAlbumsByUserId(@Path("id") userId: Int): Single<List<AlbumModel>>

}
