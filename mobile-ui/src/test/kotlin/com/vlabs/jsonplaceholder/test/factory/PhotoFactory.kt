package com.vlabs.jsonplaceholder.test.factory

import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomInt
import com.vlabs.jsonplaceholer.presentation.model.AlbumView
import com.vlabs.jsonplaceholer.presentation.model.PhotoView

class PhotoFactory {

    companion object Factory {

        fun makePhotoView(): PhotoView {
            return PhotoView(randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }
    }
}