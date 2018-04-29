package com.vlabs.jsonplaceholder.remote.mapper

import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.remote.test.factory.AlbumFactory
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
    fun mapFromRemoteMapsData() {
        val albumModel = AlbumFactory.makeAlbumModel()
        val albumEntity = albumEntityMapper.mapFromRemote(albumModel)

        assertEquals(albumModel.id, albumEntity.id)
        assertEquals(albumModel.title, albumEntity.title)
        assertEquals(albumModel.userId, albumEntity.userId)
    }
}