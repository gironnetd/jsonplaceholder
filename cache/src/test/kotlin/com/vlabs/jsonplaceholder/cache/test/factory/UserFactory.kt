package com.vlabs.jsonplaceholder.cache.test.factory

import com.vlabs.jsonplaceholder.cache.model.comments.CachedComment
import com.vlabs.jsonplaceholder.cache.model.users.CachedUser
import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.data.models.UserEntity
import com.vlabs.jsonplaceholder.cache.test.factory.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.cache.test.factory.DataFactory.Factory.randomInt

class UserFactory {

    companion object Factory {

        fun makeCachedUser(): CachedUser {
            return CachedUser(randomInt(), randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makeUserEntity(): UserEntity {
            return UserEntity(randomInt(), randomUuid(), randomUuid(), randomUuid(), DataFactory.randomUuid(), randomUuid())
        }

        fun makeUserEntityList(count: Int): List<UserEntity> {
            val bufferooEntities = mutableListOf<UserEntity>()
            repeat(count) {
                bufferooEntities.add(makeUserEntity())
            }
            return bufferooEntities
        }

        fun makeCachedUserList(count: Int): List<CachedUser> {
            val cachedUsers = mutableListOf<CachedUser>()
            repeat(count) {
                cachedUsers.add(makeCachedUser())
            }
            return cachedUsers
        }

    }

}