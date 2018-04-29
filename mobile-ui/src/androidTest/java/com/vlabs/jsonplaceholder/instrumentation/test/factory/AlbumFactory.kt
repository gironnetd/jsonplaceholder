package com.vlabs.jsonplaceholder.instrumentation.test.factory

import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.instrumentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.instrumentation.test.DataFactory.Factory.randomInt

class AlbumFactory {

    companion object Factory {

        fun makeAlbumList(count: Int): List<Album> {
            val albums = mutableListOf<Album>()
            repeat(count) {
                albums.add(makeAlbumModel())
            }
            return albums
        }

        fun makeAlbumModel(): Album {
            return Album(randomInt(), randomInt(), randomUuid())
        }
    }
}