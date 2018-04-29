package com.vlabs.jsonplaceholder.data.mappers.albums

import com.vlabs.jsonplaceholder.data.mappers.Mapper
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.domain.models.Album
import javax.inject.Inject

open class AlbumMapper @Inject constructor(): Mapper<AlbumEntity, Album> {

    /**
     * Map a [BufferooEntity] instance to a [Bufferoo] instance
     */
    override fun mapFromEntity(type: AlbumEntity): Album {
        return Album(type.userId, type.id, type.title)
    }

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: Album): AlbumEntity {
        return AlbumEntity(type.userId, type.id, type.title)
    }
}