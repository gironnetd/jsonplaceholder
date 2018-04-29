package com.vlabs.jsonplaceholder.remote.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.comments.CommentEntityMapper
import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.models.CommentModel
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumService
import com.vlabs.jsonplaceholder.remote.remote.comments.CommentRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.comments.CommentService
import com.vlabs.jsonplaceholder.remote.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.remote.test.factory.CommentFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CommentRemoteImplTest {

    private lateinit var entityMapper: CommentEntityMapper
    private lateinit var commentService: CommentService

    private lateinit var commentRemoteImpl: CommentRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        commentService = mock()
        commentRemoteImpl = CommentRemoteImpl(commentService, entityMapper)
    }

    //<editor-fold desc="Get Comments">
    @Test
    fun getCommentsCompletes() {
        stubCommentServiceGetComments(Single.just(CommentFactory.makeCommentResponse()))
        val testObserver = commentRemoteImpl.getCommentsByPostId(2).test()
        testObserver.assertComplete()
    }

    @Test
    fun getCommentsReturnsData() {
        val commentResponse = CommentFactory.makeCommentResponse()
        stubCommentServiceGetComments(Single.just(commentResponse))
        val commentEntities = mutableListOf<CommentEntity>()
        commentResponse.forEach {
            commentEntities.add(entityMapper.mapFromRemote(it))
        }

        val testObserver = commentRemoteImpl.getCommentsByPostId(2).test()
        testObserver.assertValue(commentEntities)
    }
    //</editor-fold>

    private fun stubCommentServiceGetComments(single: Single<List<CommentModel>>) {
        whenever(commentService.getCommentsByPostId(2))
                .thenReturn(single)
    }
}