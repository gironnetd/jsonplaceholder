package com.vlabs.jsonplaceholder.data.repositories.albums

import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface AlbumRemote {

    /**
     * Retrieve a list of Bufferoos, from the cache
     */
    fun getAlbumsByUserId(userId: Int): Single<List<AlbumEntity>>

}