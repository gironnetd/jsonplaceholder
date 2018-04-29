package com.vlabs.jsonplaceholder.presentation.test.factory

import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.models.User
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomInt

class UserFactory {

    companion object Factory {

        fun makeUserList(count: Int): List<User> {
            val users = mutableListOf<User>()
            repeat(count) {
                users.add(makeUserModel())
            }
            return users
        }

        fun makeUserModel(): User {
            return User(randomInt(), randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }
    }
}