package com.vlabs.jsonplaceholder.remote.mapper

import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.posts.PostEntityMapper
import com.vlabs.jsonplaceholder.remote.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.remote.test.factory.PostFactory
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
    fun mapFromRemoteMapsData() {
        val postModel = PostFactory.makePostModel()
        val postEntity = postEntityMapper.mapFromRemote(postModel)

        assertEquals(postModel.id, postEntity.id)
        assertEquals(postModel.title, postEntity.title)
        assertEquals(postModel.userId, postEntity.userId)
        assertEquals(postModel.body, postEntity.body)
    }
}