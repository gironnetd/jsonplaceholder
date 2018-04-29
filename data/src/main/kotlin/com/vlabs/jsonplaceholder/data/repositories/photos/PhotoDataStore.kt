package com.vlabs.jsonplaceholder.data.repositories.photos

import com.vlabs.jsonplaceholder.data.models.PhotoEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to Bufferroos.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface PhotoDataStore {

    fun clearPhotos(): Completable

    fun savePhotos(posts: List<PhotoEntity>): Completable

    fun getPhotosByAlbumId(albumId: Int): Single<List<PhotoEntity>>

}