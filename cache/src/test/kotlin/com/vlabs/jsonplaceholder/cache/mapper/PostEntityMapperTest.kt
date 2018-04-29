package com.vlabs.jsonplaceholder.cache.mapper

import com.vlabs.jsonplaceholder.cache.mapper.posts.PostEntityMapper
import com.vlabs.jsonplaceholder.cache.model.users.CachedPost
import com.vlabs.jsonplaceholder.cache.test.factory.PostFactory
import com.vlabs.jsonplaceholder.data.models.PostEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class PostEntityMapperTest {

    private lateinit var postEntityMapper: PostEntityMapper

    @Before
    fun setUp() {
        postEntityMapper = PostEntityMapper()
    }

    @Test
    fun mapToCachedMapsData() {
        val postEntity = PostFactory.makePostEntity()
        val cachedPost = postEntityMapper.mapToCached(postEntity)

        assertPostDataEquality(postEntity, cachedPost)
    }

    @Test
    fun mapFromCachedMapsData() {
        val cachedPost = PostFactory.makeCachedPost()
        val postEntity = postEntityMapper.mapFromCached(cachedPost)

        assertPostDataEquality(postEntity, cachedPost)
    }

    private fun assertPostDataEquality(postEntity: PostEntity,
                                        cachedPost: CachedPost) {
        assertEquals(postEntity.id, cachedPost.id)
        assertEquals(postEntity.title, cachedPost.title)
        assertEquals(postEntity.userId, cachedPost.userId)
        assertEquals(postEntity.body, cachedPost.body)
    }
}