package com.vlabs.jsonplaceholder.remote.test.factory

import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.models.CommentModel
import com.vlabs.jsonplaceholder.remote.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.remote.test.DataFactory.Factory.randomInt


class CommentFactory {

    companion object Factory {

        fun makeCommentResponse(): List<CommentModel> {
            val commentResponse = makeCommentModelList(10)
            return commentResponse
        }

        fun makeCommentModelList(count: Int): List<CommentModel> {
            val commentEntities = mutableListOf<CommentModel>()
            repeat(count) {
                commentEntities.add(makeCommentModel())
            }
            return commentEntities
        }

        fun makeCommentModel(): CommentModel {
            return CommentModel(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

    }
}