package com.vlabs.jsonplaceholer.presentation.mapper.albums

import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.presentation.mapper.Mapper
import com.vlabs.jsonplaceholer.presentation.model.AlbumView
import javax.inject.Inject

/**
 * Map a [AlbumView] to and from a [Album] instance when data is moving between
 * this layer and the Domain layer
 */
open class AlbumMapper @Inject constructor(): Mapper<AlbumView, Album> {

    /**
     * Map a [Album] instance to a [AlbumView] instance
     */
    override fun mapToView(type: Album): AlbumView {
        return AlbumView(type.userId, type.id, type.title)
    }
}