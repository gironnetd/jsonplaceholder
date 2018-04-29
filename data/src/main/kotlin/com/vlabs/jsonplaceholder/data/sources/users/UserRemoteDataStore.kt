package com.vlabs.jsonplaceholder.data.sources.users

import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.data.repositories.users.UserDataStore
import com.vlabs.jsonplaceholder.data.repositories.users.UserRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [BufferooDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class UserRemoteDataStore @Inject constructor(private val userRemote: UserRemote) :
        UserDataStore {

    override fun clearUsers(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveUsers(users: List<UserEntity>): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [BufferooEntity] instances from the API
     */
    override fun getUsers(): Single<List<UserEntity>> {
        return userRemote.getUsers()
    }

}