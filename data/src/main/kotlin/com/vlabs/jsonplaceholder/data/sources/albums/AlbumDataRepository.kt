package com.vlabs.jsonplaceholder.data.sources.albums

import com.vlabs.jsonplaceholder.data.mappers.albums.AlbumMapper
import com.vlabs.jsonplaceholder.data.mappers.posts.PostMapper
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.models.Post
import com.vlabs.jsonplaceholder.domain.repositories.albums.AlbumRepository
import com.vlabs.jsonplaceholder.domain.repositories.posts.PostRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [BufferooRepository] interface for communicating to and from
 * data sources
 */
class AlbumDataRepository @Inject constructor(private val factory: AlbumDataStoreFactory,
                                              private val albumMapper: AlbumMapper) :
        AlbumRepository {


//    override fun clearAlbums(): Completable {
//        return factory.retrieveCacheDataStore().clearPosts()
//    }
//
//    override fun saveAlbums(posts: List<Album>): Completable {
//        val albumEntities = posts.map { albumMapper.mapToEntity(it) }
//        return savePostEntities(postEntities)
//    }

    private fun saveAlbumEntities(albums: List<AlbumEntity>): Completable {
        return factory.retrieveCacheDataStore().saveAlbums(albums)
    }

    override fun getAlbumsByUserId(userId: Int): Single<List<Album>> {
        val dataStore = factory.retrieveDataStore(userId)
        return dataStore.getAlbumsByUserId(userId)
                .flatMap {
                    if (dataStore is AlbumRemoteDataStore) {
                        saveAlbumEntities(it).toSingle { it }
                    } else {
                        Single.just(it)
                    }
                }
                .map { list ->
                    list.map { listItem ->
                        albumMapper.mapFromEntity(listItem)
                    }
                }
    }

}