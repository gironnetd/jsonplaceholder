package com.vlabs.jsonplaceholder.presentation.ui.comments

import com.nhaarman.mockito_kotlin.*
import com.vlabs.jsonplaceholder.domain.interactors.comments.GetCommentsByPostId
import com.vlabs.jsonplaceholder.domain.interactors.users.GetUsers
import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholder.domain.models.User
import com.vlabs.jsonplaceholder.presentation.mapper.users.UserMapper
import com.vlabs.jsonplaceholder.presentation.test.factory.CommentFactory
import com.vlabs.jsonplaceholder.presentation.test.factory.UserFactory
import com.vlabs.jsonplaceholder.presentation.ui.posts.UsersListContract
import com.vlabs.jsonplaceholder.presentation.ui.posts.UsersListPresenter
import com.vlabs.jsonplaceholer.presentation.mapper.comments.CommentMapper
import com.vlabs.jsonplaceholer.presentation.ui.comments.CommentsContract
import com.vlabs.jsonplaceholer.presentation.ui.comments.CommentsPresenter
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CommentsPresenterTest {

    private lateinit var mockCommentsListView: CommentsContract.View
    private lateinit var mockCommentMapper: CommentMapper
    private lateinit var mockGetCommentsByPostId: GetCommentsByPostId

    private lateinit var commentsListPresenter: CommentsPresenter
    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<Comment>>>

    @Before
    fun setup() {
        captor = argumentCaptor<DisposableSingleObserver<List<Comment>>>()
        mockCommentsListView = mock()
        mockCommentMapper = mock()
        mockGetCommentsByPostId = mock()
        commentsListPresenter = CommentsPresenter(mockCommentsListView,
                mockGetCommentsByPostId, mockCommentMapper)
    }

    //<editor-fold desc="Retrieve Comments">
    @Test
    fun retrieveCommentsHidesErrorState() {
        commentsListPresenter.retrieveComments(2)

        verify(mockGetCommentsByPostId).execute(captor.capture(), eq(GetCommentsByPostId.Params.forComments(2)))
        captor.firstValue.onSuccess(CommentFactory.makeCommentList(2))
        verify(mockCommentsListView).hideErrorState()
    }

    @Test
    fun retrieveCommentsHidesEmptyState() {
        commentsListPresenter.retrieveComments(2)

        verify(mockGetCommentsByPostId).execute(captor.capture(), eq(GetCommentsByPostId.Params.forComments(2)))
        captor.firstValue.onSuccess(CommentFactory.makeCommentList(2))
        verify(mockCommentsListView).hideEmptyState()
    }

    @Test
    fun retrieveCommentsShowsComments() {
        val comments = CommentFactory.makeCommentList(2)
        commentsListPresenter.retrieveComments(2)

        verify(mockGetCommentsByPostId).execute(captor.capture(), eq(GetCommentsByPostId.Params.forComments(2)))
        captor.firstValue.onSuccess(comments)
        verify(mockCommentsListView).showComments(
                comments.map { mockCommentMapper.mapToView(it) })
    }

    @Test
    fun retrieveCommentsShowsEmptyState() {
        commentsListPresenter.retrieveComments(2)

        verify(mockGetCommentsByPostId).execute(captor.capture(), eq(GetCommentsByPostId.Params.forComments(2)))
        captor.firstValue.onSuccess(CommentFactory.makeCommentList(0))
        verify(mockCommentsListView).showEmptyState()
    }

    @Test
    fun retrieveCommentsHidesComments() {
        commentsListPresenter.retrieveComments(2)

        verify(mockGetCommentsByPostId).execute(captor.capture(), eq(GetCommentsByPostId.Params.forComments(2)))
        captor.firstValue.onSuccess(CommentFactory.makeCommentList(0))
        verify(mockCommentsListView).hideComments()
    }

    @Test
    fun retrieveCommentsShowsErrorState() {
        commentsListPresenter.retrieveComments(2)

        verify(mockGetCommentsByPostId).execute(captor.capture(), eq(GetCommentsByPostId.Params.forComments(2)))
        captor.firstValue.onError(RuntimeException())
        verify(mockCommentsListView).showErrorState()
    }

    @Test
    fun retrieveCommentsHidesCommentsWhenErrorThrown() {
        commentsListPresenter.retrieveComments(2)

        verify(mockGetCommentsByPostId).execute(captor.capture(), eq(GetCommentsByPostId.Params.forComments(2)))
        captor.firstValue.onError(RuntimeException())
        verify(mockCommentsListView).hideComments()
    }

    @Test
    fun retrieveCommentsHidesEmptyStateWhenErrorThrown() {
        commentsListPresenter.retrieveComments(2)

        verify(mockGetCommentsByPostId).execute(captor.capture(), eq(GetCommentsByPostId.Params.forComments(2)))
        captor.firstValue.onError(RuntimeException())
        verify(mockCommentsListView).hideEmptyState()
    }
}