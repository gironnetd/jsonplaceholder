package com.vlabs.jsonplaceholder.cache.mapper.users

import com.vlabs.jsonplaceholder.cache.model.users.CachedUser
import com.vlabs.jsonplaceholder.data.models.UserEntity
import org.buffer.android.boilerplate.cache.mapper.EntityMapper
import javax.inject.Inject

class UserEntityMapper @Inject constructor(): EntityMapper<CachedUser, UserEntity> {

    /**
     * Map a [BufferooEntity] instance to a [CachedBufferoo] instance
     */
    override fun mapToCached(type: UserEntity): CachedUser {
        return CachedUser(type.userId, type.name, type.userName, type.email, type.phone, type.website)
    }

    /**
     * Map a [CachedBufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapFromCached(type: CachedUser): UserEntity {
        return UserEntity(type.userId, type.name, type.userName, type.email, type.phone, type.website)
    }

}

