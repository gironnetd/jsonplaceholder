package com.vlabs.jsonplaceholder.mappers

import com.vlabs.jsonplaceholder.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.test.factory.CommentFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CommentMapperTest {

    private lateinit var commentMapper: CommentMapper

    @Before
    fun setUp() {
        commentMapper = CommentMapper()
    }

    @Test
    fun mapToViewMapsData() {
        val commentView = CommentFactory.makeCommentView()
        val commentViewModel = commentMapper.mapToViewModel(commentView)

        assertEquals(commentView.id, commentViewModel.id)
        assertEquals(commentView.postId, commentViewModel.postId)
        assertEquals(commentView.name, commentViewModel.name)
        assertEquals(commentView.email, commentViewModel.email)
        assertEquals(commentView.body, commentViewModel.body)

    }
}