package com.vlabs.jsonplaceholder.data.sources.photos

import com.vlabs.jsonplaceholder.data.repositories.photos.PhotoCache
import com.vlabs.jsonplaceholder.data.repositories.photos.PhotoDataStore
import com.vlabs.jsonplaceholder.data.repositories.posts.PostCache
import com.vlabs.jsonplaceholder.data.repositories.posts.PostDataStore
import javax.inject.Inject

/**
 * Create an instance of a TodoDataStore
 */
open class PhotoDataStoreFactory @Inject constructor(
        private val photoCache: PhotoCache,
        private val photoCacheDataStore: PhotoCacheDataStore,
        private val photoRemoteDataStore: PhotoRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(albumId: Int): PhotoDataStore {
        if (photoCache.isCached(albumId) && !photoCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): PhotoDataStore {
        return photoCacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): PhotoDataStore {
        return photoRemoteDataStore
    }

}