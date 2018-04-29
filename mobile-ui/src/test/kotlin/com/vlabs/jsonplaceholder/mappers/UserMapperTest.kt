package com.vlabs.jsonplaceholder.mappers

import com.vlabs.jsonplaceholder.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.test.factory.UserFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class UserMapperTest {

    private lateinit var userMapper: UserMapper

    @Before
    fun setUp() {
        userMapper = UserMapper()
    }

    @Test
    fun mapToViewMapsData() {
        val userView = UserFactory.makeUserView()
        val userViewModel = userMapper.mapToViewModel(userView)

        assertEquals(userView.name, userViewModel.name)
        assertEquals(userView.userName, userViewModel.userName)
        assertEquals(userView.email, userViewModel.email)
        assertEquals(userView.phone, userViewModel.phone)
        assertEquals(userView.website, userViewModel.website)
        assertEquals(userView.userId, userViewModel.userId)
    }
}