package com.vlabs.jsonplaceholder.data.sources.users

import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.data.repositories.users.UserCache
import com.vlabs.jsonplaceholder.data.repositories.users.UserDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class UserCacheDataStore @Inject constructor(private val userCache: UserCache) :
        UserDataStore {

    /**
     * Clear all Bufferoos from the cache
     */
    override fun clearUsers(): Completable {
        return userCache.clearUsers()
    }

    /**
     * Save a given [List] of [BufferooEntity] instances to the cache
     */
    override fun saveUsers(users: List<UserEntity>): Completable {
        return userCache.saveUsers(users)
                .doOnComplete {
                    userCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    /**
     * Retrieve a list of [BufferooEntity] instance from the cache
     */
    override fun getUsers(): Single<List<UserEntity>> {
        return userCache.getUsers()
    }

}