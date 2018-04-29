package com.vlabs.jsonplaceholder.data.mappers.albums

import com.vlabs.jsonplaceholder.cache.model.albums.CachedAlbum
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import org.buffer.android.boilerplate.cache.mapper.EntityMapper
import javax.inject.Inject

class AlbumEntityMapper @Inject constructor(): EntityMapper<CachedAlbum, AlbumEntity> {

    /**
     * Map a [BufferooEntity] instance to a [CachedBufferoo] instance
     */
    override fun mapToCached(type: AlbumEntity): CachedAlbum {
        return CachedAlbum(type.userId, type.id, type.title)
    }

    /**
     * Map a [CachedBufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapFromCached(type: CachedAlbum): AlbumEntity {
        return AlbumEntity(type.userId, type.id, type.title)

    }

}

