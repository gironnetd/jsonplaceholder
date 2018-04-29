package com.vlabs.jsonplaceholder.domain.usecases.comments

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.interactors.albums.GetAlbumsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.comments.GetCommentsByPostId
import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholder.domain.repositories.albums.AlbumRepository
import com.vlabs.jsonplaceholder.domain.repositories.comments.CommentRepository
import com.vlabs.jsonplaceholder.domain.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.domain.test.factory.CommentFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetCommentsByPostIdTest {

    private lateinit var getCommentsByPostId: GetCommentsByPostId

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockCommentRepository: CommentRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockCommentRepository = mock()
        getCommentsByPostId = GetCommentsByPostId(mockCommentRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getCommentsByPostId.buildUseCaseObservable(GetCommentsByPostId.Params.forComments(2))
        verify(mockCommentRepository).getCommentsByPostId(2)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubCommentRepositoryGetComments(Single.just(CommentFactory.makeCommentList(2)))
        val testObserver = getCommentsByPostId.buildUseCaseObservable(GetCommentsByPostId.Params.forComments(2)).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val comments = CommentFactory.makeCommentList(2)
        stubCommentRepositoryGetComments(Single.just(comments))
        val testObserver = getCommentsByPostId.buildUseCaseObservable(GetCommentsByPostId.Params.forComments(2)).test()
        testObserver.assertValue(comments)
    }

    private fun stubCommentRepositoryGetComments(single: Single<List<Comment>>) {
        whenever(mockCommentRepository.getCommentsByPostId(2))
                .thenReturn(single)
    }
}