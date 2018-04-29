package com.vlabs.jsonplaceholder.domain.repositories.comments

import com.vlabs.jsonplaceholder.domain.models.Comment
import io.reactivex.Single

interface CommentRepository {

    fun getCommentsByPostId(postId: Int): Single<List<Comment>>
}