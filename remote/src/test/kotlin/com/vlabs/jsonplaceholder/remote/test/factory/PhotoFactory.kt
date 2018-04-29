package com.vlabs.jsonplaceholder.remote.test.factory

import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.models.PhotoModel
import com.vlabs.jsonplaceholder.remote.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.remote.test.DataFactory.Factory.randomInt

class PhotoFactory {

    companion object Factory {

        fun makePhotoResponse(): List<PhotoModel> {
            val photoResponse = makePhotoModelList(5)
            return photoResponse
        }

        fun makePhotoModelList(count: Int): List<PhotoModel> {
            val photoEntities = mutableListOf<PhotoModel>()
            repeat(count) {
                photoEntities.add(makePhotoModel())
            }
            return photoEntities
        }

        fun makePhotoModel(): PhotoModel {
            return PhotoModel(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

    }
}