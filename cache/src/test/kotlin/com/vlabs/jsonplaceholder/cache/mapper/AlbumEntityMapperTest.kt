package com.vlabs.jsonplaceholder.cache.mapper

import com.vlabs.jsonplaceholder.cache.model.albums.CachedAlbum
import com.vlabs.jsonplaceholder.cache.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.data.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class AlbumEntityMapperTest {

    private lateinit var albumEntityMapper: AlbumEntityMapper

    @Before
    fun setUp() {
        albumEntityMapper = AlbumEntityMapper()
    }

    @Test
    fun mapToCachedMapsData() {
        val albumEntity = AlbumFactory.makeAlbumEntity()
        val cachedAlbum = albumEntityMapper.mapToCached(albumEntity)

        assertAlbumDataEquality(albumEntity, cachedAlbum)
    }

    @Test
    fun mapFromCachedMapsData() {
        val cachedAlbum = AlbumFactory.makeCachedAlbum()
        val albumEntity = albumEntityMapper.mapFromCached(cachedAlbum)

        assertAlbumDataEquality(albumEntity, cachedAlbum)
    }

    private fun assertAlbumDataEquality(albumEntity: AlbumEntity,
                                           cachedAlbum: CachedAlbum) {
        assertEquals(albumEntity.id, cachedAlbum.id)
        assertEquals(albumEntity.title, cachedAlbum.title)
        assertEquals(albumEntity.userId, cachedAlbum.userId)
    }

}