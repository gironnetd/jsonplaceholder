package com.vlabs.jsonplaceholder.domain.usecases.posts

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.interactors.albums.GetAlbumsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.posts.GetPostsByUserId
import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.models.Post
import com.vlabs.jsonplaceholder.domain.repositories.albums.AlbumRepository
import com.vlabs.jsonplaceholder.domain.repositories.posts.PostRepository
import com.vlabs.jsonplaceholder.domain.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.domain.test.factory.PostFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetPostsByUserIdTest {

    private lateinit var getPostsByUserId: GetPostsByUserId

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockPostRepository: PostRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockPostRepository = mock()
        getPostsByUserId = GetPostsByUserId(mockPostRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getPostsByUserId.buildUseCaseObservable(GetPostsByUserId.Params.forPosts(2))
        verify(mockPostRepository).getPostsByUserId(2)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubPostRepositoryGetPosts(Single.just(PostFactory.makePostList(2)))
        val testObserver = getPostsByUserId.buildUseCaseObservable(GetPostsByUserId.Params.forPosts(2)).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val posts = PostFactory.makePostList(2)
        stubPostRepositoryGetPosts(Single.just(posts))
        val testObserver = getPostsByUserId.buildUseCaseObservable(GetPostsByUserId.Params.forPosts(2)).test()
        testObserver.assertValue(posts)
    }

    private fun stubPostRepositoryGetPosts(single: Single<List<Post>>) {
        whenever(mockPostRepository.getPostsByUserId(2))
                .thenReturn(single)
    }
}