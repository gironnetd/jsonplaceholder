package com.vlabs.jsonplaceholer.presentation.ui.photos

import com.vlabs.jsonplaceholder.presentation.BasePresenter
import com.vlabs.jsonplaceholder.presentation.BaseView
import com.vlabs.jsonplaceholer.presentation.model.PhotoView

interface PhotosContract {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun showPhotos(users: List<PhotoView>)

        fun hidePhotos()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {

        fun start(albumId: Int)

        fun retrievePhotos(albumId: Int)

    }

}