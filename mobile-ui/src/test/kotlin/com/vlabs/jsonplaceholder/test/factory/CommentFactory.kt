package com.vlabs.jsonplaceholder.test.factory

import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomInt
import com.vlabs.jsonplaceholer.presentation.model.AlbumView
import com.vlabs.jsonplaceholer.presentation.model.CommentView

class CommentFactory {

    companion object Factory {

        fun makeCommentView(): CommentView {
            return CommentView(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }
    }
}