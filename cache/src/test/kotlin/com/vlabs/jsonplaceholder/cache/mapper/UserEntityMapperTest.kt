package com.vlabs.jsonplaceholder.cache.mapper

import com.vlabs.jsonplaceholder.cache.mapper.users.UserEntityMapper
import com.vlabs.jsonplaceholder.cache.model.users.CachedUser
import com.vlabs.jsonplaceholder.cache.test.factory.UserFactory
import com.vlabs.jsonplaceholder.data.models.UserEntity
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
    fun mapToCachedMapsData() {
        val userEntity = UserFactory.makeUserEntity()
        val cachedUser = userEntityMapper.mapToCached(userEntity)

        assertUserDataEquality(userEntity, cachedUser)
    }

    @Test
    fun mapFromCachedMapsData() {
        val cachedUser = UserFactory.makeCachedUser()
        val userEntity = userEntityMapper.mapFromCached(cachedUser)

        assertUserDataEquality(userEntity, cachedUser)
    }

    private fun assertUserDataEquality(userEntity: UserEntity,
                                        cachedUser: CachedUser) {
        assertEquals(userEntity.name, cachedUser.name)
        assertEquals(userEntity.userName, cachedUser.userName)
        assertEquals(userEntity.userId, cachedUser.userId)
        assertEquals(userEntity.email, cachedUser.email)
        assertEquals(userEntity.phone, cachedUser.phone)
        assertEquals(userEntity.website, cachedUser.website)
    }
}