package com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.album

import com.vlabs.jsonplaceholder.presentation.BasePresenter
import com.vlabs.jsonplaceholder.presentation.BaseView
import com.vlabs.jsonplaceholer.presentation.model.AlbumView

interface AlbumsContract {

    interface View : BaseView<Presenter> {

        //fun showUsers(users: List<UserView>)
        fun showAlbums(albums: List<AlbumView>)

        fun hideAlbums()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {

        fun start(userId: Int)

        fun retrieveAlbums(userId: Int)
    }
}