package com.vlabs.jsonplaceholer.presentation.ui.comments

import com.vlabs.jsonplaceholder.presentation.BasePresenter
import com.vlabs.jsonplaceholder.presentation.BaseView
import com.vlabs.jsonplaceholer.presentation.model.CommentView

interface CommentsContract {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun showComments(comments: List<CommentView>)

        fun hideComments()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {

        fun start(postId: Int)

        fun retrieveComments(postId: Int)

    }

}