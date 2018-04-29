package com.vlabs.jsonplaceholder.instrumentation.test.factory

import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholder.instrumentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.instrumentation.test.DataFactory.Factory.randomInt

class CommentFactory {

    companion object Factory {

        fun makeCommentList(count: Int): List<Comment> {
            val comments = mutableListOf<Comment>()
            repeat(count) {
                comments.add(makeCommentModel())
            }
            return comments
        }

        fun makeCommentModel(): Comment {
            return Comment(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }
    }
}