package com.vlabs.jsonplaceholder.cache.test.factory

import com.vlabs.jsonplaceholder.cache.model.comments.CachedComment
import com.vlabs.jsonplaceholder.cache.model.photos.CachedPhoto
import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.cache.test.factory.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.cache.test.factory.DataFactory.Factory.randomInt
import com.vlabs.jsonplaceholder.data.models.PhotoEntity

class PhotoFactory {

    companion object Factory {

        fun makeCachedPhoto(): CachedPhoto {
            return CachedPhoto(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makePhotoEntity(): PhotoEntity {
            return PhotoEntity(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makePhotoEntityList(count: Int): List<PhotoEntity> {
            val bufferooEntities = mutableListOf<PhotoEntity>()
            repeat(count) {
                bufferooEntities.add(makePhotoEntity())
            }
            return bufferooEntities
        }

        fun makeCachedPhotoList(count: Int): List<CachedPhoto> {
            val cachedPhotos = mutableListOf<CachedPhoto>()
            repeat(count) {
                cachedPhotos.add(makeCachedPhoto())
            }
            return cachedPhotos
        }

    }

}