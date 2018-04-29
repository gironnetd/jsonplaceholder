package com.vlabs.jsonplaceholder.cache.test.factory

import com.vlabs.jsonplaceholder.cache.model.comments.CachedComment
import com.vlabs.jsonplaceholder.cache.model.users.CachedPost
import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.cache.test.factory.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.cache.test.factory.DataFactory.Factory.randomInt
import com.vlabs.jsonplaceholder.data.models.PostEntity

class PostFactory {

    companion object Factory {

        fun makeCachedPost(): CachedPost {
            return CachedPost(randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makePostEntity(): PostEntity {
            return PostEntity(randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makePostEntityList(count: Int): List<PostEntity> {
            val bufferooEntities = mutableListOf<PostEntity>()
            repeat(count) {
                bufferooEntities.add(makePostEntity())
            }
            return bufferooEntities
        }

        fun makeCachedPostList(count: Int): List<CachedPost> {
            val cachedPosts = mutableListOf<CachedPost>()
            repeat(count) {
                cachedPosts.add(makeCachedPost())
            }
            return cachedPosts
        }

    }

}