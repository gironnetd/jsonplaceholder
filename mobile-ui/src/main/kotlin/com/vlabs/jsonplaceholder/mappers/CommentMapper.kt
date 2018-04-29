package com.vlabs.jsonplaceholder.mappers

import com.vlabs.jsonplaceholder.model.CommentViewModel
import com.vlabs.jsonplaceholer.presentation.model.CommentView
import javax.inject.Inject

open class CommentMapper @Inject constructor(): Mapper<CommentViewModel, CommentView> {

    /**
     * Map a [CommentView] instance to a [CommentViewModel] instance
     */
    override fun mapToViewModel(type: CommentView): CommentViewModel {
        return CommentViewModel(type.postId, type.id, type.name, type.email, type.body)
    }
}