package com.vlabs.jsonplaceholder.data.sources.photos

import com.vlabs.jsonplaceholder.data.models.PhotoEntity
import com.vlabs.jsonplaceholder.data.repositories.photos.PhotoCache
import com.vlabs.jsonplaceholder.data.repositories.photos.PhotoDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class PhotoCacheDataStore @Inject constructor(private val photoCache: PhotoCache) :
        PhotoDataStore {

    /**
     * Clear all Bufferoos from the cache
     */
    override fun clearPhotos(): Completable {
        return photoCache.clearPhotos()
    }

    /**
     * Save a given [List] of [BufferooEntity] instances to the cache
     */
    override fun savePhotos(posts: List<PhotoEntity>): Completable {
        return photoCache.savePhotos(posts)
                .doOnComplete {
                    photoCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    /**
     * Retrieve a list of [BufferooEntity] instance from the cache
     */

    override fun getPhotosByAlbumId(albumId: Int): Single<List<PhotoEntity>> {
        return photoCache.getPhotosByAlbumId(albumId)
    }
}