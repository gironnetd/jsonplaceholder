package com.vlabs.jsonplaceholder.remote.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.data.models.PhotoEntity
import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.photos.PhotoEntityMapper
import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.models.PhotoModel
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumService
import com.vlabs.jsonplaceholder.remote.remote.photos.PhotoRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.photos.PhotoService
import com.vlabs.jsonplaceholder.remote.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.remote.test.factory.PhotoFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PhotoRemoteImplTest {

    private lateinit var entityMapper: PhotoEntityMapper
    private lateinit var photoService: PhotoService

    private lateinit var photoRemoteImpl: PhotoRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        photoService = mock()
        photoRemoteImpl = PhotoRemoteImpl(photoService, entityMapper)
    }

    //<editor-fold desc="Get Photos">
    @Test
    fun getPhotosCompletes() {
        stubPhotoServiceGetPhotos(Single.just(PhotoFactory.makePhotoResponse()))
        val testObserver = photoRemoteImpl.getPhotosByAlbumId(2).test()
        testObserver.assertComplete()
    }

    @Test
    fun getPhotosReturnsData() {
        val photoResponse = PhotoFactory.makePhotoResponse()
        stubPhotoServiceGetPhotos(Single.just(photoResponse))
        val photoEntities = mutableListOf<PhotoEntity>()
        photoResponse.forEach {
            photoEntities.add(entityMapper.mapFromRemote(it))
        }

        val testObserver = photoRemoteImpl.getPhotosByAlbumId(2).test()
        testObserver.assertValue(photoEntities)
    }
    //</editor-fold>

    private fun stubPhotoServiceGetPhotos(single: Single<List<PhotoModel>>) {
        whenever(photoService.getPhotosByAlbumId(2))
                .thenReturn(single)
    }
}