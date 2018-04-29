package com.vlabs.jsonplaceholder.mappers

import com.vlabs.jsonplaceholder.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.test.factory.PostFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class PostMapperTest {

    private lateinit var postMapper: PostMapper

    @Before
    fun setUp() {
        postMapper = PostMapper()
    }

    @Test
    fun mapToViewMapsData() {
        val postView = PostFactory.makePostView()
        val postViewModel = postMapper.mapToViewModel(postView)

        assertEquals(postView.id, postViewModel.id)
        assertEquals(postView.title, postViewModel.title)
        assertEquals(postView.userId, postViewModel.userId)
        assertEquals(postView.body, postViewModel.body)
    }
}