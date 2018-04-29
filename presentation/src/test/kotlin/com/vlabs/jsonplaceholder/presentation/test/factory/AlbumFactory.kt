package com.vlabs.jsonplaceholder.presentation.test.factory

import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomInt

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