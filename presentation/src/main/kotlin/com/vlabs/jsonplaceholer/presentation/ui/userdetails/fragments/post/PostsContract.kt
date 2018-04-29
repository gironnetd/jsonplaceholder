package com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.post

import com.vlabs.jsonplaceholder.presentation.BasePresenter
import com.vlabs.jsonplaceholder.presentation.BaseView
import com.vlabs.jsonplaceholer.presentation.model.PostView

interface PostsContract {

    interface View : BaseView<Presenter> {

        fun showPosts(posts: List<PostView>)

        fun hidePosts()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {
        fun retrievePosts(userId: Int)
    }

}