package com.vlabs.jsonplaceholder.data.mappers.users

import com.vlabs.jsonplaceholder.data.mappers.Mapper
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.domain.models.User
import javax.inject.Inject

open class UserMapper @Inject constructor(): Mapper<UserEntity, User> {

    /**
     * Map a [BufferooEntity] instance to a [Bufferoo] instance
     */
    override fun mapFromEntity(type: UserEntity): User {
        return User(type.userId, type.name, type.userName, type.email, type.phone, type.website)
    }

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: User): UserEntity {
        return UserEntity(type.userId, type.name, type.userName, type.email, type.phone, type.website)
    }
}

