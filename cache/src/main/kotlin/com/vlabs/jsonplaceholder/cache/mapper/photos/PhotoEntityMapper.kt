package com.vlabs.jsonplaceholder.data.mappers.photos

import com.vlabs.jsonplaceholder.cache.model.photos.CachedPhoto
import com.vlabs.jsonplaceholder.data.models.PhotoEntity
import org.buffer.android.boilerplate.cache.mapper.EntityMapper
import javax.inject.Inject

class PhotoEntityMapper @Inject constructor(): EntityMapper<CachedPhoto, PhotoEntity> {

    /**
     * Map a [BufferooEntity] instance to a [CachedBufferoo] instance
     */
    override fun mapToCached(type: PhotoEntity): CachedPhoto {
        return CachedPhoto(type.albumId, type.id, type.title, type.url, type.thumbnailUrl)
    }

    /**
     * Map a [CachedBufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapFromCached(type: CachedPhoto): PhotoEntity {
        return PhotoEntity(type.albumId, type.id, type.title, type.url, type.thumbnailUrl)

    }

}