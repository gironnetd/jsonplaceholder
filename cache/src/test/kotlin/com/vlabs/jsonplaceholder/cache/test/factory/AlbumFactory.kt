package com.vlabs.jsonplaceholder.cache.test.factory

import com.vlabs.jsonplaceholder.cache.model.albums.CachedAlbum
import com.vlabs.jsonplaceholder.cache.test.factory.DataFactory.Factory.randomInt
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.cache.test.factory.DataFactory.Factory.randomUuid


class AlbumFactory {

    companion object Factory {

        fun makeCachedAlbum(): CachedAlbum {
            return CachedAlbum(randomInt(), randomInt(), randomUuid())
        }

        fun makeAlbumEntity(): AlbumEntity {
            return AlbumEntity(randomInt(), randomInt(), randomUuid())
        }

        fun makeAlbumEntityList(count: Int): List<AlbumEntity> {
            val bufferooEntities = mutableListOf<AlbumEntity>()
            repeat(count) {
                bufferooEntities.add(makeAlbumEntity())
            }
            return bufferooEntities
        }

        fun makeCachedAlbumList(count: Int): List<CachedAlbum> {
            val cachedAlbums = mutableListOf<CachedAlbum>()
            repeat(count) {
                cachedAlbums.add(makeCachedAlbum())
            }
            return cachedAlbums
        }

    }

}
