package com.vlabs.jsonplaceholder.domain.test.factory

import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.domain.test.DataFactory.Factory.randomInt

class AlbumFactory {

    companion object Factory {

        fun makeAlbumList(count: Int): List<Album> {
            val albums = mutableListOf<Album>()
            repeat(count) {
                albums.add(makeAlbum())
            }
            return albums
        }

        fun makeAlbum(): Album {
            return Album(randomInt(), randomInt(), randomUuid())
        }

    }
}