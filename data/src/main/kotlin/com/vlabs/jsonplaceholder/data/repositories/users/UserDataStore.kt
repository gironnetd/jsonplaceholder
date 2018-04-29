package com.vlabs.jsonplaceholder.data.repositories.users

import com.vlabs.jsonplaceholder.data.models.BufferooEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to Bufferroos.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface UserDataStore {

    fun clearUsers(): Completable

    fun saveUsers(users: List<UserEntity>): Completable

    fun getUsers(): Single<List<UserEntity>>

}