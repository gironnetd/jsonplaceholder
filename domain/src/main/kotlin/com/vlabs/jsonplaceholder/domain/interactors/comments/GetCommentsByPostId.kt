package com.vlabs.jsonplaceholder.domain.interactors.comments

import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholder.domain.repositories.comments.CommentRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetCommentsByPostId @Inject constructor(val commentRepository: CommentRepository,
                                                  threadExecutor: ThreadExecutor,
                                                  postExecutionThread: PostExecutionThread):
        SingleUseCase<List<Comment>, GetCommentsByPostId.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Single<List<Comment>> {
        return commentRepository.getCommentsByPostId(params!!.postId)
    }

    class Params private constructor(internal val postId: Int) {
        companion object {

            fun forComments(postId: Int): Params {
                return Params(postId)
            }
        }
    }
}
