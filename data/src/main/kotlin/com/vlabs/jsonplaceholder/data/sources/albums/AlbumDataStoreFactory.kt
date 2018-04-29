package com.vlabs.jsonplaceholder.data.sources.albums

import com.vlabs.jsonplaceholder.data.repositories.albums.AlbumCache
import com.vlabs.jsonplaceholder.data.repositories.albums.AlbumDataStore
import com.vlabs.jsonplaceholder.data.repositories.posts.PostCache
import com.vlabs.jsonplaceholder.data.repositories.posts.PostDataStore
import javax.inject.Inject

/**
 * Create an instance of a TodoDataStore
 */
open class AlbumDataStoreFactory @Inject constructor(
        private val albumCache: AlbumCache,
        private val albumCacheDataStore: AlbumCacheDataStore,
        private val albumRemoteDataStore: AlbumRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(userId: Int): AlbumDataStore {
        if (albumCache.isCached(userId) && !albumCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): AlbumDataStore {
        return albumCacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): AlbumDataStore {
        return albumRemoteDataStore
    }

}