package com.vlabs.jsonplaceholder.remote.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumService
import com.vlabs.jsonplaceholder.remote.test.factory.AlbumFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AlbumRemoteImplTest {

    private lateinit var entityMapper: AlbumEntityMapper
    private lateinit var albumService: AlbumService

    private lateinit var albumRemoteImpl: AlbumRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        albumService = mock()
        albumRemoteImpl = AlbumRemoteImpl(albumService, entityMapper)
    }

    //<editor-fold desc="Get Albums">
    @Test
    fun getAlbumsCompletes() {
        stubAlbumServiceGetAlbums(Single.just(AlbumFactory.makeAlbumResponse()))
        val testObserver = albumRemoteImpl.getAlbumsByUserId(2).test()
        testObserver.assertComplete()
    }

    @Test
    fun getAlbumsReturnsData() {
        val albumResponse = AlbumFactory.makeAlbumResponse()
        stubAlbumServiceGetAlbums(Single.just(albumResponse))
        val albumEntities = mutableListOf<AlbumEntity>()
        albumResponse.forEach {
            albumEntities.add(entityMapper.mapFromRemote(it))
        }

        val testObserver = albumRemoteImpl.getAlbumsByUserId(2).test()
        testObserver.assertValue(albumEntities)
    }
    //</editor-fold>

    private fun stubAlbumServiceGetAlbums(single: Single<List<AlbumModel>>) {
        whenever(albumService.getAlbumsByUserId(2))
                .thenReturn(single)
    }
}