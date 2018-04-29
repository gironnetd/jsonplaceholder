package com.vlabs.jsonplaceholder.data.sources.albums

import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.data.repositories.albums.AlbumDataStore
import com.vlabs.jsonplaceholder.data.repositories.albums.AlbumRemote
import com.vlabs.jsonplaceholder.data.repositories.posts.PostDataStore
import com.vlabs.jsonplaceholder.data.repositories.posts.PostRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class AlbumRemoteDataStore @Inject constructor(private val albumRemote: AlbumRemote) :
        AlbumDataStore {

    override fun clearAlbums(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveAlbums(posts: List<AlbumEntity>): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [BufferooEntity] instances from the API
     */
//    override fun getAlbums(): Single<List<PostEntity>> {
//        return albumRemote.getAlbumsByUserId()
//    }

    override fun getAlbumsByUserId(userId: Int): Single<List<AlbumEntity>> {

        return albumRemote.getAlbumsByUserId(userId)
    }

}