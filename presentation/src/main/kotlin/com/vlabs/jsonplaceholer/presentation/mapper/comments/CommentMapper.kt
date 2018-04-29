package com.vlabs.jsonplaceholer.presentation.mapper.comments

import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholder.presentation.mapper.Mapper
import com.vlabs.jsonplaceholer.presentation.model.CommentView
import javax.inject.Inject

/**
 * Map a [PhotoView] to and from a [Photo] instance when data is moving between
 * this layer and the Domain layer
 */
open class CommentMapper @Inject constructor(): Mapper<CommentView, Comment> {

    /**
     * Map a [Comment] instance to a [CommentView] instance
     */
    override fun mapToView(type: Comment): CommentView {
        return CommentView(type.postId, type.id, type.name, type.email, type.body)
    }
}