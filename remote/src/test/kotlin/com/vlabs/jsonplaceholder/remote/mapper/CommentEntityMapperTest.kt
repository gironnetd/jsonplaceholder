package com.vlabs.jsonplaceholder.remote.mapper

import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.comments.CommentEntityMapper
import com.vlabs.jsonplaceholder.remote.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.remote.test.factory.CommentFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CommentEntityMapperTest {

    private lateinit var commentEntityMapper: CommentEntityMapper

    @Before
    fun setUp() {
        commentEntityMapper = CommentEntityMapper()
    }

    @Test
    fun mapFromRemoteMapsData() {
        val commentModel = CommentFactory.makeCommentModel()
        val commentEntity = commentEntityMapper.mapFromRemote(commentModel)

        assertEquals(commentModel.id, commentEntity.id)
        assertEquals(commentModel.postId, commentEntity.postId)
        assertEquals(commentModel.name, commentEntity.name)
        assertEquals(commentModel.email, commentEntity.email)
        assertEquals(commentModel.body, commentEntity.body)
    }
}