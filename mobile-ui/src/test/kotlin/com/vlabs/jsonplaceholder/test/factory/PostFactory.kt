package com.vlabs.jsonplaceholder.test.factory

import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomInt
import com.vlabs.jsonplaceholer.presentation.model.AlbumView
import com.vlabs.jsonplaceholer.presentation.model.PostView


class PostFactory {

    companion object Factory {

        fun makePostView(): PostView {
            return PostView(randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }
    }
}