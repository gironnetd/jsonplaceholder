package com.vlabs.jsonplaceholder.presentation.ui.posts

import com.vlabs.jsonplaceholder.presentation.BasePresenter
import com.vlabs.jsonplaceholder.presentation.BaseView
import com.vlabs.jsonplaceholder.presentation.model.UserView


/**
 * Defines a contract of operations between the Browse Presenter and Browse View
 */
interface UsersListContract {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun showUsers(users: List<UserView>)

        fun hideUsers()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {

        fun retrieveUsers()

    }

}