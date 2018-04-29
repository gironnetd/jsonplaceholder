package com.vlabs.jsonplaceholder.remote.remote.photos

import com.vlabs.jsonplaceholder.remote.models.PhotoModel
import com.vlabs.jsonplaceholder.remote.models.PostModel
import com.vlabs.jsonplaceholder.remote.models.UserModel
import com.vlabs.jsonplaceholder.remote.remote.Service
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface PhotoService {
    //https://jsonplaceholder.typicode.com/users/1/posts
    @GET("albums/{id}/photos")
    fun getPhotosByAlbumId(@Path("id") albumId: Int): Single<List<PhotoModel>>
}
