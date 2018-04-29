package com.vlabs.jsonplaceholer.presentation.ui.comments

import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.interactors.comments.GetCommentsByPostId
import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholer.presentation.mapper.comments.CommentMapper
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class CommentsPresenter @Inject constructor(val viewList: CommentsContract.View,
                                          val getCommentsByPostIdUseCase: SingleUseCase<List<Comment>, GetCommentsByPostId.Params>,
                                          val userMapper: CommentMapper): CommentsContract.Presenter {

    init {
        viewList.setPresenter(this)
    }

    override fun start() {
        //retrieveComments(1)
    }

    override fun start(postId: Int) {
        retrieveComments(postId)
    }

    override fun stop() {
        getCommentsByPostIdUseCase.dispose()
    }

    override fun retrieveComments(postId: Int) {
        getCommentsByPostIdUseCase.execute(CommentSubscriber(), GetCommentsByPostId.Params.forComments(postId))
    }

    internal fun handleGetCommentsSuccess(users: List<Comment>) {
        viewList.hideErrorState()
        if (users.isNotEmpty()) {
            viewList.hideEmptyState()
            viewList.showComments(users.map { userMapper.mapToView(it) })
        } else {
            viewList.hideComments()
            viewList.showEmptyState()
        }
    }

    inner class CommentSubscriber: DisposableSingleObserver<List<Comment>>() {

        override fun onSuccess(comment: List<Comment>) {
            handleGetCommentsSuccess(comment)
        }

        override fun onError(exception: Throwable) {
            viewList.hideComments()
            viewList.hideEmptyState()
            viewList.showErrorState()
        }
    }
}