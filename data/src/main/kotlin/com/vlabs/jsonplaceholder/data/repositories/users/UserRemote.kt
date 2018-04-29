package com.vlabs.jsonplaceholder.data.repositories.users

import com.vlabs.jsonplaceholder.data.models.BufferooEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface UserRemote {

    /**
     * Retrieve a list of Bufferoos, from the cache
     */
    fun getUsers(): Single<List<UserEntity>>

}