package com.vlabs.jsonplaceholder.remote.test.factory

import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumService
import com.vlabs.jsonplaceholder.remote.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.remote.test.DataFactory.Factory.randomInt

class AlbumFactory {

    companion object Factory {

        fun makeAlbumResponse(): List<AlbumModel> {
            val albumResponse = makeAlbumModelList(5)
            return albumResponse
        }

        fun makeAlbumModelList(count: Int): List<AlbumModel> {
            val albumEntities = mutableListOf<AlbumModel>()
            repeat(count) {
                albumEntities.add(makeAlbumModel())
            }
            return albumEntities
        }

        fun makeAlbumModel(): AlbumModel {
            return AlbumModel(randomInt(), randomInt(), randomUuid())
        }

    }
}