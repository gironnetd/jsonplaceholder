package com.vlabs.jsonplaceholer.presentation.mapper.posts

import com.vlabs.jsonplaceholder.domain.models.Post
import com.vlabs.jsonplaceholder.presentation.mapper.Mapper
import com.vlabs.jsonplaceholer.presentation.model.PostView
import javax.inject.Inject

/**
 * Map a [PostView] to and from a [Post] instance when data is moving between
 * this layer and the Domain layer
 */
open class PostMapper @Inject constructor(): Mapper<PostView, Post> {

    /**
     * Map a [Bufferoo] instance to a [BufferooView] instance
     */
    override fun mapToView(type: Post): PostView {
        return PostView(type.userId, type.id, type.title, type.body)
    }
}