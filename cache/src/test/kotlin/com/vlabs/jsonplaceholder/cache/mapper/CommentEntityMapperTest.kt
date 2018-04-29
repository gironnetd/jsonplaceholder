package com.vlabs.jsonplaceholder.cache.mapper

import com.vlabs.jsonplaceholder.cache.model.comments.CachedComment
import com.vlabs.jsonplaceholder.cache.test.factory.CommentFactory
import com.vlabs.jsonplaceholder.data.mappers.comments.CommentEntityMapper
import com.vlabs.jsonplaceholder.data.models.CommentEntity
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
    fun mapToCachedMapsData() {
        val commentEntity = CommentFactory.makeCommentEntity()
        val cachedComment = commentEntityMapper.mapToCached(commentEntity)

        assertCommentDataEquality(commentEntity, cachedComment)
    }

    @Test
    fun mapFromCachedMapsData() {
        val cachedComment = CommentFactory.makeCachedComment()
        val commentEntity = commentEntityMapper.mapFromCached(cachedComment)

        assertCommentDataEquality(commentEntity, cachedComment)
    }

    private fun assertCommentDataEquality(commentEntity: CommentEntity,
                                        cachedComment: CachedComment) {
        assertEquals(commentEntity.id, cachedComment.id)
        assertEquals(commentEntity.postId, cachedComment.postId)
        assertEquals(commentEntity.name, cachedComment.name)
        assertEquals(commentEntity.email, cachedComment.email)
        assertEquals(commentEntity.body, cachedComment.body)

    }
}