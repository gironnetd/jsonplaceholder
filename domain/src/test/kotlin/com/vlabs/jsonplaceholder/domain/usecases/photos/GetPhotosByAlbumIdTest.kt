package com.vlabs.jsonplaceholder.domain.usecases.photos

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.interactors.albums.GetAlbumsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.photos.GetPhotosByAlbumId
import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.models.Photo
import com.vlabs.jsonplaceholder.domain.repositories.albums.AlbumRepository
import com.vlabs.jsonplaceholder.domain.repositories.photos.PhotoRepository
import com.vlabs.jsonplaceholder.domain.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.domain.test.factory.PhotoFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetPhotosByAlbumIdTest {

    private lateinit var getPhotosByAlbumId: GetPhotosByAlbumId

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockPhotoRepository: PhotoRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockPhotoRepository = mock()
        getPhotosByAlbumId = GetPhotosByAlbumId(mockPhotoRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getPhotosByAlbumId.buildUseCaseObservable(GetPhotosByAlbumId.Params.forPhotos(2))
        verify(mockPhotoRepository).getPhotosByAlbumId(2)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubPhotoRepositoryGetPhotos(Single.just(PhotoFactory.makePhotoList(2)))
        val testObserver = getPhotosByAlbumId.buildUseCaseObservable(GetPhotosByAlbumId.Params.forPhotos(2)).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val photos = PhotoFactory.makePhotoList(2)
        stubPhotoRepositoryGetPhotos(Single.just(photos))
        val testObserver = getPhotosByAlbumId.buildUseCaseObservable(GetPhotosByAlbumId.Params.forPhotos(2)).test()
        testObserver.assertValue(photos)
    }

    private fun stubPhotoRepositoryGetPhotos(single: Single<List<Photo>>) {
        whenever(mockPhotoRepository.getPhotosByAlbumId(2))
                .thenReturn(single)
    }
}