package com.vlabs.jsonplaceholder.presentation.test.factory

import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.models.Post
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomInt

class PostFactory {

    companion object Factory {

        fun makePostList(count: Int): List<Post> {
            val posts = mutableListOf<Post>()
            repeat(count) {
                posts.add(makePostModel())
            }
            return posts
        }

        fun makePostModel(): Post {
            return Post(randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }
    }
}