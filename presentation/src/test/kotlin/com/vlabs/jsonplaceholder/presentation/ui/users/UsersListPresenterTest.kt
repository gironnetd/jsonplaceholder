package com.vlabs.jsonplaceholder.presentation.ui.users

import com.nhaarman.mockito_kotlin.*
import com.vlabs.jsonplaceholder.domain.interactors.users.GetUsers
import com.vlabs.jsonplaceholder.domain.models.User
import com.vlabs.jsonplaceholder.presentation.mapper.users.UserMapper
import com.vlabs.jsonplaceholder.presentation.test.factory.UserFactory
import com.vlabs.jsonplaceholder.presentation.ui.posts.UsersListContract
import com.vlabs.jsonplaceholder.presentation.ui.posts.UsersListPresenter
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UsersListPresenterTest {

    private lateinit var mockUsersListView: UsersListContract.View
    private lateinit var mockUserMapper: UserMapper
    private lateinit var mockGetUsers: GetUsers

    private lateinit var usersListPresenter: UsersListPresenter
    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<User>>>

    @Before
    fun setup() {
        captor = argumentCaptor<DisposableSingleObserver<List<User>>>()
        mockUsersListView = mock()
        mockUserMapper = mock()
        mockGetUsers = mock()
        usersListPresenter = UsersListPresenter(mockUsersListView,
                mockGetUsers, mockUserMapper)
    }

    //<editor-fold desc="Retrieve Users">
    @Test
    fun retrieveUsersHidesErrorState() {
        usersListPresenter.retrieveUsers()

        verify(mockGetUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(UserFactory.makeUserList(2))
        verify(mockUsersListView).hideErrorState()
    }

    @Test
    fun retrieveUsersHidesEmptyState() {
        usersListPresenter.retrieveUsers()

        verify(mockGetUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(UserFactory.makeUserList(2))
        verify(mockUsersListView).hideEmptyState()
    }

    @Test
    fun retrieveUsersShowsUsers() {
        val users = UserFactory.makeUserList(2)
        usersListPresenter.retrieveUsers()

        verify(mockGetUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(users)
        verify(mockUsersListView).showUsers(
                users.map { mockUserMapper.mapToView(it) })
    }

    @Test
    fun retrieveUsersShowsEmptyState() {
        usersListPresenter.retrieveUsers()

        verify(mockGetUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(UserFactory.makeUserList(0))
        verify(mockUsersListView).showEmptyState()
    }

    @Test
    fun retrieveUsersHidesUsers() {
        usersListPresenter.retrieveUsers()

        verify(mockGetUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(UserFactory.makeUserList(0))
        verify(mockUsersListView).hideUsers()
    }

    @Test
    fun retrieveUsersShowsErrorState() {
        usersListPresenter.retrieveUsers()

        verify(mockGetUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        verify(mockUsersListView).showErrorState()
    }

    @Test
    fun retrieveUsersHidesUsersWhenErrorThrown() {
        usersListPresenter.retrieveUsers()

        verify(mockGetUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        verify(mockUsersListView).hideUsers()
    }

    @Test
    fun retrieveUsersHidesEmptyStateWhenErrorThrown() {
        usersListPresenter.retrieveUsers()

        verify(mockGetUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        verify(mockUsersListView).hideEmptyState()
    }
}