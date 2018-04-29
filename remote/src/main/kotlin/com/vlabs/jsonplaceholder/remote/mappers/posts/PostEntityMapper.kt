package com.vlabs.jsonplaceholder.remote.mappers.posts

import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.remote.mappers.EntityMapper
import com.vlabs.jsonplaceholder.remote.models.PostModel
import com.vlabs.jsonplaceholder.remote.models.UserModel
import javax.inject.Inject

/**
 * Map a [PostModel] to and from a [PostEntity] instance when data is moving between
 * this later and the Data layer
 */
open class PostEntityMapper @Inject constructor(): EntityMapper<PostModel, PostEntity> {

    /**
     * Map an instance of a [PostModel] to a [PostEntity] model
     */
    override fun mapFromRemote(type: PostModel): PostEntity {
        return  PostEntity(type.userId, type.id, type.title, type.body)
    }

    override fun mapFromRemote(type: List<PostModel>): List<PostEntity> {
        return type.map { mapFromRemote(it) }
    }
}