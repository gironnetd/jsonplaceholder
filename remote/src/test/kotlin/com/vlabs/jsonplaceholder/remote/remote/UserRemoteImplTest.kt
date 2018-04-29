package com.vlabs.jsonplaceholder.remote.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.users.UserEntityMapper
import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.models.UserModel
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumService
import com.vlabs.jsonplaceholder.remote.remote.users.UserRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.users.UserService
import com.vlabs.jsonplaceholder.remote.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.remote.test.factory.UserFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserRemoteImplTest {

    private lateinit var entityMapper: UserEntityMapper
    private lateinit var userService: UserService

    private lateinit var userRemoteImpl: UserRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        userService = mock()
        userRemoteImpl = UserRemoteImpl(userService, entityMapper)
    }

    //<editor-fold desc="Get Users">
    @Test
    fun getUsersCompletes() {
        stubUserServiceGetUsers(Single.just(UserFactory.makeUserResponse()))
        val testObserver = userRemoteImpl.getUsers().test()
        testObserver.assertComplete()
    }

    @Test
    fun getUsersReturnsData() {
        val userResponse = UserFactory.makeUserResponse()
        stubUserServiceGetUsers(Single.just(userResponse))
        val userEntities = mutableListOf<UserEntity>()
        userResponse.forEach {
            userEntities.add(entityMapper.mapFromRemote(it))
        }

        val testObserver = userRemoteImpl.getUsers().test()
        testObserver.assertValue(userEntities)
    }
    //</editor-fold>

    private fun stubUserServiceGetUsers(single: Single<List<UserModel>>) {
        whenever(userService.getUsers())
                .thenReturn(single)
    }
}