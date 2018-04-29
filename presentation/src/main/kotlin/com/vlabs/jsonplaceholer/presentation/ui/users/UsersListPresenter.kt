package com.vlabs.jsonplaceholder.presentation.ui.posts

import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.models.User
import com.vlabs.jsonplaceholder.presentation.mapper.users.UserMapper
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class UsersListPresenter @Inject constructor(val viewList: UsersListContract.View,
                                             val getUsersUseCase: SingleUseCase<List<User>, Void>,
                                             val userMapper: UserMapper):
        UsersListContract.Presenter {

    init {
        viewList.setPresenter(this)
    }

    override fun start() {
        retrieveUsers()
    }

    override fun stop() {
        getUsersUseCase.dispose()
    }

    override fun retrieveUsers() {
        getUsersUseCase.execute(UserSubscriber())
    }

    internal fun handleGetUsersSuccess(users: List<User>) {
        viewList.hideErrorState()
        if (users.isNotEmpty()) {
            viewList.hideEmptyState()
            viewList.showUsers(users.map { userMapper.mapToView(it) })
        } else {
            viewList.hideUsers()
            viewList.showEmptyState()
        }
    }

    inner class UserSubscriber: DisposableSingleObserver<List<User>>() {

        override fun onSuccess(t: List<User>) {
            handleGetUsersSuccess(t)
        }

        override fun onError(exception: Throwable) {
            viewList.hideUsers()
            viewList.hideEmptyState()
            viewList.showErrorState()
        }

    }

}