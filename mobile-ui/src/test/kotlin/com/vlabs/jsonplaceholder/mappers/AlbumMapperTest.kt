package com.vlabs.jsonplaceholder.mappers

import com.vlabs.jsonplaceholder.test.factory.AlbumFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class AlbumMapperTest {

    private lateinit var albumMapper: AlbumMapper

    @Before
    fun setUp() {
        albumMapper = AlbumMapper()
    }

    @Test
    fun mapToViewMapsData() {
        val albumView = AlbumFactory.makeAlbumView()
        val albumViewModel = albumMapper.mapToViewModel(albumView)

        assertEquals(albumView.id, albumViewModel.id)
        assertEquals(albumView.title, albumViewModel.title)
        assertEquals(albumView.userId, albumViewModel.userId)
    }
}