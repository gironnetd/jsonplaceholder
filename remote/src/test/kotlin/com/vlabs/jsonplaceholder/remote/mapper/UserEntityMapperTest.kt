package com.vlabs.jsonplaceholder.remote.mapper

import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.users.UserEntityMapper
import com.vlabs.jsonplaceholder.remote.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.remote.test.factory.UserFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class UserEntityMapperTest {

    private lateinit var userEntityMapper: UserEntityMapper

    @Before
    fun setUp() {
        userEntityMapper = UserEntityMapper()
    }

    @Test
    fun mapFromRemoteMapsData() {
        val userModel = UserFactory.makeUserModel()
        val userEntity = userEntityMapper.mapFromRemote(userModel)

        assertEquals(userModel.userId, userEntity.userId)
        assertEquals(userModel.name, userEntity.name)
        assertEquals(userModel.userName, userEntity.userName)
        assertEquals(userModel.email, userEntity.email)
        assertEquals(userModel.phone, userEntity.phone)
        assertEquals(userModel.website, userEntity.website)
        //assertEquals(userModel.address, userEntity.a)
    }
}