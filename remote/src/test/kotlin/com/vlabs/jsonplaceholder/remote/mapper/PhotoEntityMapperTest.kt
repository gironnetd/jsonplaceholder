package com.vlabs.jsonplaceholder.remote.mapper

import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.photos.PhotoEntityMapper
import com.vlabs.jsonplaceholder.remote.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.remote.test.factory.PhotoFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class PhotoEntityMapperTest {

    private lateinit var photoEntityMapper: PhotoEntityMapper

    @Before
    fun setUp() {
        photoEntityMapper = PhotoEntityMapper()
    }

    @Test
    fun mapFromRemoteMapsData() {
        val photoModel = PhotoFactory.makePhotoModel()
        val photoEntity = photoEntityMapper.mapFromRemote(photoModel)

        assertEquals(photoModel.id, photoEntity.id)
        assertEquals(photoModel.title, photoEntity.title)
        assertEquals(photoModel.albumId, photoEntity.albumId)
        assertEquals(photoModel.url, photoEntity.url)
        assertEquals(photoModel.thumbnailUrl, photoEntity.thumbnailUrl)
    }
}