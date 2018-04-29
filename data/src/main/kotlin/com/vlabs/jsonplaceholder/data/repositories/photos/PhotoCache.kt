package com.vlabs.jsonplaceholder.data.repositories.photos

import com.vlabs.jsonplaceholder.data.models.PhotoEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface PhotoCache {

    /**
     * Clear all Bufferoos from the cache
     */
    fun clearPhotos(): Completable

    /**
     * Save a given list of Bufferoos to the cache
     */
    fun savePhotos(photos: List<PhotoEntity>): Completable

    /**
     * Retrieve a list of Bufferoos, from the cache
     */
    fun getPhotos(): Single<List<PhotoEntity>>

    fun getPhotosByAlbumId(albumId: Int): Single<List<PhotoEntity>>

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(albumId: Int): Boolean

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