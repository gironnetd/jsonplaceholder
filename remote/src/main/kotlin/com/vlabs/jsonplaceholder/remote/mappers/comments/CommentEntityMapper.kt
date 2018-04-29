package com.vlabs.jsonplaceholder.remote.mappers.comments

import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.remote.mappers.EntityMapper
import com.vlabs.jsonplaceholder.remote.models.CommentModel
import com.vlabs.jsonplaceholder.remote.models.PostModel
import com.vlabs.jsonplaceholder.remote.models.UserModel
import javax.inject.Inject

/**
 * Map a [PostModel] to and from a [PostEntity] instance when data is moving between
 * this later and the Data layer
 */
open class CommentEntityMapper @Inject constructor(): EntityMapper<CommentModel, CommentEntity> {

    /**
     * Map an instance of a [CommentModel] to a [CommentModel] model
     */
    override fun mapFromRemote(type: CommentModel): CommentEntity {
        return  CommentEntity(type.postId, type.id, type.name,type.email, type.body)
    }

    override fun mapFromRemote(type: List<CommentModel>): List<CommentEntity> {
        return type.map { mapFromRemote(it) }
    }
}