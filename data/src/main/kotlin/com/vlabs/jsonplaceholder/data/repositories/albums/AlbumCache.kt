package com.vlabs.jsonplaceholder.data.repositories.albums

import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface AlbumCache {

    /**
     * Clear all Bufferoos from the cache
     */
    fun clearAlbums(): Completable

    /**
     * Save a given list of Bufferoos to the cache
     */
    fun saveAlbums(posts: List<AlbumEntity>): Completable

    /**
     * Retrieve a list of Bufferoos, from the cache
     */
    fun getAlbums(): Single<List<AlbumEntity>>

    fun getAlbumsByUserId(userId: Int): Single<List<AlbumEntity>>
    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(userId: Int): Boolean

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Checks if the cache is expired.

     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(): Boolean

}