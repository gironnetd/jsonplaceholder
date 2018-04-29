package com.vlabs.jsonplaceholder.remote.mappers.albums

import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.remote.mappers.EntityMapper
import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.models.PostModel
import com.vlabs.jsonplaceholder.remote.models.UserModel
import javax.inject.Inject

/**
 * Map a [PostModel] to and from a [PostEntity] instance when data is moving between
 * this later and the Data layer
 */
open class AlbumEntityMapper @Inject constructor(): EntityMapper<AlbumModel, AlbumEntity> {

    /**
     * Map an instance of a [AlbumModel] to a [AlbumEntity] model
     */
    override fun mapFromRemote(type: AlbumModel): AlbumEntity {
        return  AlbumEntity(type.userId, type.id, type.title)
    }

    override fun mapFromRemote(type: List<AlbumModel>): List<AlbumEntity> {
        return type.map { mapFromRemote(it) }
    }
}