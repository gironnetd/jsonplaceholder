package com.vlabs.jsonplaceholder.domain.test.factory

import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholder.domain.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.domain.test.DataFactory.Factory.randomInt

class CommentFactory {

    companion object Factory {

        fun makeCommentList(count: Int): List<Comment> {
            val comments = mutableListOf<Comment>()
            repeat(count) {
                comments.add(makeComment())
            }
            return comments
        }

        fun makeComment(): Comment {
            return Comment(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

    }
}