package com.vlabs.jsonplaceholder.data.sources.users

import com.vlabs.jsonplaceholder.data.repositories.users.UserCache
import com.vlabs.jsonplaceholder.data.repositories.users.UserDataStore
import javax.inject.Inject

/**
 * Create an instance of a TodoDataStore
 */
open class UserDataStoreFactory @Inject constructor(
        private val userCache: UserCache,
        private val userCacheDataStore: UserCacheDataStore,
        private val userRemoteDataStore: UserRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(): UserDataStore {
        if (userCache.isCached() && !userCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): UserDataStore {
        return userCacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): UserDataStore {
        return userRemoteDataStore
    }

}