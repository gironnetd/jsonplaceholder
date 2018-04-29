package com.vlabs.jsonplaceholder.mappers

import com.vlabs.jsonplaceholder.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.test.factory.PhotoFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class PhotoMapperTest {

    private lateinit var photoMapper: PhotoMapper

    @Before
    fun setUp() {
        photoMapper = PhotoMapper()
    }

    @Test
    fun mapToViewMapsData() {
        val photoView = PhotoFactory.makePhotoView()
        val photoViewModel = photoMapper.mapToViewModel(photoView)

        assertEquals(photoView.id, photoViewModel.id)
        assertEquals(photoView.title, photoViewModel.title)
        assertEquals(photoView.albumId, photoViewModel.albumId)
        assertEquals(photoView.url, photoViewModel.url)
        assertEquals(photoView.thumbnailUrl, photoViewModel.thumbnailUrl)

    }
}