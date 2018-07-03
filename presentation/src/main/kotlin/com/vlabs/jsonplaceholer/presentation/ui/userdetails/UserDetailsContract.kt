package com.vlabs.jsonplaceholer.presentation.ui.userdetails

import com.vlabs.jsonplaceholder.presentation.BasePresenter
import com.vlabs.jsonplaceholder.presentation.BaseView
import com.vlabs.jsonplaceholer.presentation.model.AlbumView
import com.vlabs.jsonplaceholer.presentation.model.PostView
import com.vlabs.jsonplaceholer.presentation.model.TodoView

interface UserDetailsContract {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        //fun showUsers(users: List<UserView>)
        //fun showAlbums(albums: List<AlbumView>)

        ///fun hideAlbums()

        //fun showPosts(posts: List<PostView>)

        //fun hidePosts()

        //fun showTodos(todos: List<TodoView>)

        //fun hideTodos()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()


    }

    interface Presenter : BasePresenter {

        fun start(userId: Int)

        //fun retrieveAlbums(userId: Int)

        //fun retrievePosts(userId: Int)

        //fun retrieveTodos(userId: Int)

    }

}