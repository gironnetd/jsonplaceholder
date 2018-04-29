package com.vlabs.jsonplaceholder.remote.remote.users

import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.data.repositories.users.UserRemote
import com.vlabs.jsonplaceholder.remote.mappers.users.UserEntityMapper
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [BufferooRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */

@Singleton
class UserRemoteImpl @Inject constructor(private val userService: UserService,
                                         private val entityMapper: UserEntityMapper) :
        UserRemote {

    /**
     * Retrieve a list of [UserEntity] instances from the [UserService].
     */
    override fun getUsers(): Single<List<UserEntity>> {
        return userService.getUsers()
                .map {
                    //it.users.map { listItem ->
                        entityMapper.mapFromRemote(it)
                    //}
                }
    }

}