package com.vlabs.jsonplaceholder.data.sources.photos

import com.vlabs.jsonplaceholder.data.models.PhotoEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.data.repositories.photos.PhotoDataStore
import com.vlabs.jsonplaceholder.data.repositories.photos.PhotoRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class PhotoRemoteDataStore @Inject constructor(private val photoRemote: PhotoRemote) :
        PhotoDataStore {


    override fun clearPhotos(): Completable {
        throw UnsupportedOperationException()
    }

    override fun savePhotos(posts: List<PhotoEntity>): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [BufferooEntity] instances from the API
     */
//    override fun getPhotos(): Single<List<PhotoEntity>> {
//        return photoRemote.getPhotos()
//    }

    override fun getPhotosByAlbumId(albumId: Int): Single<List<PhotoEntity>> {
        return photoRemote.getPhotosByAlbumId(albumId)
    }

}