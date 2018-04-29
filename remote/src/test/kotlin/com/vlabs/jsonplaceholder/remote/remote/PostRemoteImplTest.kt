package com.vlabs.jsonplaceholder.remote.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.posts.PostEntityMapper
import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.models.PostModel
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumService
import com.vlabs.jsonplaceholder.remote.remote.posts.PostRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.posts.PostService
import com.vlabs.jsonplaceholder.remote.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.remote.test.factory.PostFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PostRemoteImplTest {

    private lateinit var entityMapper: PostEntityMapper
    private lateinit var postService: PostService

    private lateinit var postRemoteImpl: PostRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        postService = mock()
        postRemoteImpl = PostRemoteImpl(postService, entityMapper)
    }

    //<editor-fold desc="Get Posts">
    @Test
    fun getPostsCompletes() {
        stubPostServiceGetPosts(Single.just(PostFactory.makePostResponse()))
        val testObserver = postRemoteImpl.getPostsByUserId(2).test()
        testObserver.assertComplete()
    }

    @Test
    fun getPostsReturnsData() {
        val postResponse = PostFactory.makePostResponse()
        stubPostServiceGetPosts(Single.just(postResponse))
        val postEntities = mutableListOf<PostEntity>()
        postResponse.forEach {
            postEntities.add(entityMapper.mapFromRemote(it))
        }

        val testObserver = postRemoteImpl.getPostsByUserId(2).test()
        testObserver.assertValue(postEntities)
    }
    //</editor-fold>

    private fun stubPostServiceGetPosts(single: Single<List<PostModel>>) {
        whenever(postService.getPostsByUserId(2))
                .thenReturn(single)
    }
}