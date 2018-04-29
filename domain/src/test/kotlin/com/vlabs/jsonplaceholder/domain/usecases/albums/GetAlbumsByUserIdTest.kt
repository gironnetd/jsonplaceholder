package com.vlabs.jsonplaceholder.domain.usecases.albums

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.interactors.albums.GetAlbumsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.users.GetUsers
import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.models.User
import com.vlabs.jsonplaceholder.domain.repositories.albums.AlbumRepository
import com.vlabs.jsonplaceholder.domain.repositories.users.UserRepository
import com.vlabs.jsonplaceholder.domain.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.domain.test.factory.UserFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetAlbumsByUserIdTest {

    private lateinit var getAlbumsByUserId: GetAlbumsByUserId

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockAlbumRepository: AlbumRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockAlbumRepository = mock()
        getAlbumsByUserId = GetAlbumsByUserId(mockAlbumRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getAlbumsByUserId.buildUseCaseObservable(GetAlbumsByUserId.Params.forAlbums(2))
        verify(mockAlbumRepository).getAlbumsByUserId(2)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubAlbumRepositoryGetAlbums(Single.just(AlbumFactory.makeAlbumList(2)))
        val testObserver = getAlbumsByUserId.buildUseCaseObservable(GetAlbumsByUserId.Params.forAlbums(2)).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val albums = AlbumFactory.makeAlbumList(2)
        stubAlbumRepositoryGetAlbums(Single.just(albums))
        val testObserver = getAlbumsByUserId.buildUseCaseObservable(GetAlbumsByUserId.Params.forAlbums(2)).test()
        testObserver.assertValue(albums)
    }

    private fun stubAlbumRepositoryGetAlbums(single: Single<List<Album>>) {
        whenever(mockAlbumRepository.getAlbumsByUserId(2))
                .thenReturn(single)
    }
}