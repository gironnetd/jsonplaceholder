package com.vlabs.jsonplaceholder.data.mappers.comments

import com.vlabs.jsonplaceholder.data.mappers.Mapper
import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.domain.models.Comment
import javax.inject.Inject

open class CommentMapper @Inject constructor(): Mapper<CommentEntity, Comment> {

    /**
     * Map a [BufferooEntity] instance to a [Bufferoo] instance
     */
    override fun mapFromEntity(type: CommentEntity): Comment {
        return Comment(type.postId, type.id, type.name,type.email, type.body)
    }

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: Comment): CommentEntity {
        return CommentEntity(type.postId, type.id, type.name,type.email, type.body)
    }
}