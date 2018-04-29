package com.vlabs.jsonplaceholer.presentation.mapper.photos

import com.vlabs.jsonplaceholder.domain.models.Photo
import com.vlabs.jsonplaceholder.presentation.mapper.Mapper
import com.vlabs.jsonplaceholer.presentation.model.PhotoView
import javax.inject.Inject

/**
 * Map a [PhotoView] to and from a [Photo] instance when data is moving between
 * this layer and the Domain layer
 */
open class PhotoMapper @Inject constructor(): Mapper<PhotoView, Photo> {

    /**
     * Map a [Photo] instance to a [PhotoView] instance
     */
    override fun mapToView(type: Photo): PhotoView {
        return PhotoView(type.albumId, type.id, type.title, type.url, type.thumbnailUrl)
    }
}