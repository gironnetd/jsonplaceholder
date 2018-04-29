package com.vlabs.jsonplaceholder.cache.mapper

import com.vlabs.jsonplaceholder.cache.model.photos.CachedPhoto
import com.vlabs.jsonplaceholder.cache.test.factory.PhotoFactory
import com.vlabs.jsonplaceholder.data.mappers.photos.PhotoEntityMapper
import com.vlabs.jsonplaceholder.data.models.PhotoEntity
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
    fun mapToCachedMapsData() {
        val photoEntity = PhotoFactory.makePhotoEntity()
        val cachedPhoto = photoEntityMapper.mapToCached(photoEntity)

        assertPhotoDataEquality(photoEntity, cachedPhoto)
    }

    @Test
    fun mapFromCachedMapsData() {
        val cachedPhoto = PhotoFactory.makeCachedPhoto()
        val photoEntity = photoEntityMapper.mapFromCached(cachedPhoto)

        assertPhotoDataEquality(photoEntity, cachedPhoto)
    }

    private fun assertPhotoDataEquality(photoEntity: PhotoEntity,
                                        cachedPhoto: CachedPhoto) {
        assertEquals(photoEntity.id, cachedPhoto.id)
        assertEquals(photoEntity.title, cachedPhoto.title)
        assertEquals(photoEntity.albumId, cachedPhoto.albumId)
        assertEquals(photoEntity.url, cachedPhoto.url)
        assertEquals(photoEntity.thumbnailUrl, cachedPhoto.thumbnailUrl)
    }
}