package com.vlabs.jsonplaceholder.data.mappers.posts

import com.vlabs.jsonplaceholder.data.mappers.Mapper
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.domain.models.Post
import javax.inject.Inject

open class PostMapper @Inject constructor(): Mapper<PostEntity, Post> {

    /**
     * Map a [BufferooEntity] instance to a [Bufferoo] instance
     */
    override fun mapFromEntity(type: PostEntity): Post {
        return Post(type.userId, type.id, type.title, type.body)
    }

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: Post): PostEntity {
        return PostEntity(type.userId, type.id, type.title, type.body)
    }
}


