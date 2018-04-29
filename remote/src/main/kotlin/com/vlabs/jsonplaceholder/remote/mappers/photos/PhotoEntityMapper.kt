package com.vlabs.jsonplaceholder.remote.mappers.photos

import com.vlabs.jsonplaceholder.data.models.PhotoEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.remote.mappers.EntityMapper
import com.vlabs.jsonplaceholder.remote.models.PhotoModel
import com.vlabs.jsonplaceholder.remote.models.PostModel
import com.vlabs.jsonplaceholder.remote.models.UserModel
import javax.inject.Inject

/**
 * Map a [PostModel] to and from a [PostEntity] instance when data is moving between
 * this later and the Data layer
 */
open class PhotoEntityMapper @Inject constructor(): EntityMapper<PhotoModel, PhotoEntity> {

    /**
     * Map an instance of a [PhotoModel] to a [PhotoEntity] model
     */
    override fun mapFromRemote(type: PhotoModel): PhotoEntity {
        return  PhotoEntity(type.albumId, type.id, type.title, type.url, type.thumbnailUrl)
    }

    override fun mapFromRemote(type: List<PhotoModel>): List<PhotoEntity> {
        return type.map { mapFromRemote(it) }
    }
}