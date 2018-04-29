package com.vlabs.jsonplaceholder.instrumentation.test.factory

import com.vlabs.jsonplaceholder.domain.models.Photo
import com.vlabs.jsonplaceholder.instrumentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.instrumentation.test.DataFactory.Factory.randomInt

class PhotoFactory {

    companion object Factory {

        fun makePhotoList(count: Int): List<Photo> {
            val photos = mutableListOf<Photo>()
            repeat(count) {
                photos.add(makePhotoModel())
            }
            return photos
        }

        fun makePhotoModel(): Photo {
            return Photo(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }
    }
}