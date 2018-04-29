package com.vlabs.jsonplaceholder.mappers

import com.vlabs.jsonplaceholder.model.AlbumViewModel
import com.vlabs.jsonplaceholer.presentation.model.AlbumView
import javax.inject.Inject

open class AlbumMapper @Inject constructor(): Mapper<AlbumViewModel, AlbumView> {

    /**
     * Map a [PostView] instance to a [PostViewModel] instance
     */
    override fun mapToViewModel(type: AlbumView): AlbumViewModel {
        return AlbumViewModel(type.userId, type.id, type.title)
    }
}