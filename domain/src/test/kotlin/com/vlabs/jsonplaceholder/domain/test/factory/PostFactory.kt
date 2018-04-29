package com.vlabs.jsonplaceholder.domain.test.factory

import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholder.domain.models.Post
import com.vlabs.jsonplaceholder.domain.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.domain.test.DataFactory.Factory.randomInt

class PostFactory {

    companion object Factory {

        fun makePostList(count: Int): List<Post> {
            val posts = mutableListOf<Post>()
            repeat(count) {
                posts.add(makePost())
            }
            return posts
        }

        fun makePost(): Post {
            return Post(randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

    }
}