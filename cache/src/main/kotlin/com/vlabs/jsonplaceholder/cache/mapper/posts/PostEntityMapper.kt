package com.vlabs.jsonplaceholder.cache.mapper.posts

import com.vlabs.jsonplaceholder.cache.model.users.CachedPost
import com.vlabs.jsonplaceholder.data.models.PostEntity
import org.buffer.android.boilerplate.cache.mapper.EntityMapper
import javax.inject.Inject

class PostEntityMapper @Inject constructor(): EntityMapper<CachedPost, PostEntity> {

    /**
     * Map a [BufferooEntity] instance to a [CachedBufferoo] instance
     */
    override fun mapToCached(type: PostEntity): CachedPost {
        return CachedPost(type.userId, type.id, type.title, type.body)
    }

    /**
     * Map a [CachedBufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapFromCached(type: CachedPost): PostEntity {
        return PostEntity(type.userId, type.id, type.title, type.body)

    }

}

