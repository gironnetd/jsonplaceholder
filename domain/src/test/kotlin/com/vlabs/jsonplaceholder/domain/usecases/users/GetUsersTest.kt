package com.vlabs.jsonplaceholder.domain.usecases.users

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.interactors.users.GetUsers
import com.vlabs.jsonplaceholder.domain.models.User
import com.vlabs.jsonplaceholder.domain.repositories.users.UserRepository
import com.vlabs.jsonplaceholder.domain.test.factory.UserFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetUsersTest {

    private lateinit var getUsers: GetUsers

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockUserRepository: UserRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockUserRepository = mock()
        getUsers = GetUsers(mockUserRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getUsers.buildUseCaseObservable(null)
        verify(mockUserRepository).getUsers()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubUserRepositoryGetUsers(Single.just(UserFactory.makeUserList(2)))
        val testObserver = getUsers.buildUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val users = UserFactory.makeUserList(2)
        stubUserRepositoryGetUsers(Single.just(users))
        val testObserver = getUsers.buildUseCaseObservable(null).test()
        testObserver.assertValue(users)
    }

    private fun stubUserRepositoryGetUsers(single: Single<List<User>>) {
        whenever(mockUserRepository.getUsers())
                .thenReturn(single)
    }

}