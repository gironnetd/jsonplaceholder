package com.vlabs.jsonplaceholder.mappers

import com.vlabs.jsonplaceholder.model.PostViewModel
import com.vlabs.jsonplaceholer.presentation.model.PostView
import javax.inject.Inject

/**
 * Map a [PostView] to and from a [PostViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class PostMapper @Inject constructor(): Mapper<PostViewModel, PostView> {

    /**
     * Map a [PostView] instance to a [PostViewModel] instance
     */
    override fun mapToViewModel(type: PostView): PostViewModel {
        return PostViewModel(type.userId, type.id, type.title, type.body)
    }
}