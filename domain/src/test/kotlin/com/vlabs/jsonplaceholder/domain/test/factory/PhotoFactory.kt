package com.vlabs.jsonplaceholder.domain.test.factory

import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholder.domain.models.Photo
import com.vlabs.jsonplaceholder.domain.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.domain.test.DataFactory.Factory.randomInt

class PhotoFactory {

    companion object Factory {

        fun makePhotoList(count: Int): List<Photo> {
            val photos = mutableListOf<Photo>()
            repeat(count) {
                photos.add(makePhoto())
            }
            return photos
        }

        fun makePhoto(): Photo {
            return Photo(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

    }
}