package com.vlabs.jsonplaceholder.test.factory

import com.vlabs.jsonplaceholer.presentation.model.AlbumView
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomInt


class AlbumFactory {

    companion object Factory {

        fun makeAlbumView(): AlbumView {
            return AlbumView(randomInt(), randomInt(), randomUuid())
        }
    }
}