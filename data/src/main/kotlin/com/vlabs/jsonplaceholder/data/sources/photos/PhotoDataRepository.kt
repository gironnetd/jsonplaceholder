package com.vlabs.jsonplaceholder.data.sources.photos

import com.vlabs.jsonplaceholder.data.mappers.photos.PhotoMapper
import com.vlabs.jsonplaceholder.data.models.PhotoEntity
import com.vlabs.jsonplaceholder.domain.models.Photo
import com.vlabs.jsonplaceholder.domain.repositories.photos.PhotoRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [BufferooRepository] interface for communicating to and from
 * data sources
 */
class PhotoDataRepository @Inject constructor(private val factory: PhotoDataStoreFactory,
                                              private val photoMapper: PhotoMapper) :
        PhotoRepository {

//    override fun clearPhotos(): Completable {
//        return factory.retrieveCacheDataStore().clearPhotos()
//    }
//
//    override fun savePhotos(posts: List<Photo>): Completable {
//        val postEntities = posts.map { photoMapper.mapToEntity(it) }
//        return savePhotoEntities(postEntities)
//    }

    private fun savePhotoEntities(posts: List<PhotoEntity>): Completable {
        return factory.retrieveCacheDataStore().savePhotos(posts)
    }

    override fun getPhotosByAlbumId(albumId: Int): Single<List<Photo>> {
        val dataStore = factory.retrieveDataStore(albumId)
        return dataStore.getPhotosByAlbumId(albumId)
                .flatMap {
                    if (dataStore is PhotoRemoteDataStore) {
                        savePhotoEntities(it).toSingle { it }
                    } else {
                        Single.just(it)
                    }
                }
                .map { list ->
                    list.map { listItem ->
                        photoMapper.mapFromEntity(listItem)
                    }
                }
    }

}