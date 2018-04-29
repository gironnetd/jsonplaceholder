package com.vlabs.jsonplaceholder.data.repositories.photos

import com.vlabs.jsonplaceholder.data.models.PhotoEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface PhotoRemote {

    /**
     * Retrieve a list of Bufferoos, from the cache
     */
    //fun getPosts(): Single<List<PostEntity>>
    fun getPhotosByAlbumId(albumId: Int): Single<List<PhotoEntity>>

}