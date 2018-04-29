package com.vlabs.jsonplaceholder.data.sources.albums
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.repositories.albums.AlbumCache
import com.vlabs.jsonplaceholder.data.repositories.albums.AlbumDataStore
import com.vlabs.jsonplaceholder.data.repositories.posts.PostCache
import com.vlabs.jsonplaceholder.data.repositories.posts.PostDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class AlbumCacheDataStore @Inject constructor(private val albumCache: AlbumCache) :
        AlbumDataStore {

    /**
     * Clear all Bufferoos from the cache
     */
    override fun clearAlbums(): Completable {
        return albumCache.clearAlbums()
    }

    /**
     * Save a given [List] of [BufferooEntity] instances to the cache
     */
    override fun saveAlbums(posts: List<AlbumEntity>): Completable {
        return albumCache.saveAlbums(posts)
                .doOnComplete {
                    albumCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    /**
     * Retrieve a list of [BufferooEntity] instance from the cache
     */
    override fun getAlbumsByUserId(userId: Int): Single<List<AlbumEntity>> {
        return albumCache.getAlbumsByUserId(userId)
    }

}