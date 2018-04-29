package com.vlabs.jsonplaceholder.remote.mappers.users

import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.remote.mappers.EntityMapper
import com.vlabs.jsonplaceholder.remote.models.UserModel
import javax.inject.Inject

/**
 * Map a [BufferooModel] to and from a [BufferooEntity] instance when data is moving between
 * this later and the Data layer
 */
open class UserEntityMapper @Inject constructor(): EntityMapper<UserModel, UserEntity> {

    /**
     * Map an instance of a [UserModel] to a [UserEntity] model
     */
    override fun mapFromRemote(type: UserModel): UserEntity {
        return UserEntity(type.userId.toInt(), type.name, type.userName, type.email, type.phone, type.website)
    }

    override fun mapFromRemote(type: List<UserModel>): List<UserEntity> {
        return type.map { mapFromRemote(it) }
    }
}