package com.vlabs.jsonplaceholder.data.sources.users

import com.vlabs.jsonplaceholder.data.mappers.users.UserMapper
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.domain.models.User
import com.vlabs.jsonplaceholder.domain.repositories.users.UserRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [BufferooRepository] interface for communicating to and from
 * data sources
 */
class UserDataRepository @Inject constructor(private val factory: UserDataStoreFactory,
                                             private val userMapper: UserMapper) :
        UserRepository {

    override fun clearUsers(): Completable {
        return factory.retrieveCacheDataStore().clearUsers()
    }

    override fun saveUsers(bufferoos: List<User>): Completable {
        val bufferooEntities = bufferoos.map { userMapper.mapToEntity(it) }
        return saveUserEntities(bufferooEntities)
    }

    private fun saveUserEntities(users: List<UserEntity>): Completable {
        return factory.retrieveCacheDataStore().saveUsers(users)
    }

    override fun getUsers(): Single<List<User>> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.getUsers()
                .flatMap {
                    if (dataStore is UserRemoteDataStore) {
                        saveUserEntities(it).toSingle { it }
                    } else {
                        Single.just(it)
                    }
                }
                .map { list ->
                    list.map { listItem ->
                        userMapper.mapFromEntity(listItem)
                    }
                }
    }

}