package com.vlabs.jsonplaceholder.data.mappers.comments

import com.vlabs.jsonplaceholder.cache.model.comments.CachedComment
import com.vlabs.jsonplaceholder.data.models.CommentEntity
import org.buffer.android.boilerplate.cache.mapper.EntityMapper
import javax.inject.Inject

class CommentEntityMapper @Inject constructor(): EntityMapper<CachedComment, CommentEntity> {

    /**
     * Map a [BufferooEntity] instance to a [CachedBufferoo] instance
     */
    override fun mapToCached(type: CommentEntity): CachedComment {
        return CachedComment(type.postId, type.id,type.name, type.email, type.body)
    }

    /**
     * Map a [CachedBufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapFromCached(type: CachedComment): CommentEntity {
        return CommentEntity(type.postId, type.id,type.name, type.email, type.body)

    }

}