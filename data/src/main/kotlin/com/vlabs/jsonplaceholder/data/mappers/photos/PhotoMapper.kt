package com.vlabs.jsonplaceholder.data.mappers.photos

import com.vlabs.jsonplaceholder.data.mappers.Mapper
import com.vlabs.jsonplaceholder.data.models.PhotoEntity
import com.vlabs.jsonplaceholder.domain.models.Photo
import javax.inject.Inject

open class PhotoMapper @Inject constructor(): Mapper<PhotoEntity, Photo> {

    /**
     * Map a [BufferooEntity] instance to a [Bufferoo] instance
     */
    override fun mapFromEntity(type: PhotoEntity): Photo {
        return Photo(type.albumId, type.id, type.title, type.url, type.thumbnailUrl)
    }

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: Photo): PhotoEntity {
        return PhotoEntity(type.albumId, type.id, type.title, type.url, type.thumbnailUrl)
    }
}