package com.vlabs.jsonplaceholder.mappers

import com.vlabs.jsonplaceholder.model.PhotoViewModel
import com.vlabs.jsonplaceholer.presentation.model.PhotoView
import javax.inject.Inject

open class PhotoMapper @Inject constructor(): Mapper<PhotoViewModel, PhotoView> {

    /**
     * Map a [PhotoView] instance to a [PhotoViewModel] instance
     */
    override fun mapToViewModel(type: PhotoView): PhotoViewModel {
        return PhotoViewModel(type.albumId, type.id, type.title, type.url, type.thumbnailUrl)
    }
}