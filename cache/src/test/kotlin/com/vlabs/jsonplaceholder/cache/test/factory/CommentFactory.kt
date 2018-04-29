package com.vlabs.jsonplaceholder.cache.test.factory

import com.vlabs.jsonplaceholder.cache.model.comments.CachedComment
import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.cache.test.factory.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.cache.test.factory.DataFactory.Factory.randomInt

class CommentFactory {

    companion object Factory {

        fun makeCachedComment(): CachedComment {
            return CachedComment(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makeCommentEntity(): CommentEntity {
            return CommentEntity(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makeCommentEntityList(count: Int): List<CommentEntity> {
            val bufferooEntities = mutableListOf<CommentEntity>()
            repeat(count) {
                bufferooEntities.add(makeCommentEntity())
            }
            return bufferooEntities
        }

        fun makeCachedCommentList(count: Int): List<CachedComment> {
            val cachedComments = mutableListOf<CachedComment>()
            repeat(count) {
                cachedComments.add(makeCachedComment())
            }
            return cachedComments
        }

    }

}