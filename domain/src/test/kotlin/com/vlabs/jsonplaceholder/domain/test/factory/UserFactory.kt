package com.vlabs.jsonplaceholder.domain.test.factory

import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholder.domain.models.User
import com.vlabs.jsonplaceholder.domain.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.domain.test.DataFactory.Factory.randomInt

class UserFactory {

    companion object Factory {

        fun makeUserList(count: Int): List<User> {
            val users = mutableListOf<User>()
            repeat(count) {
                users.add(makeUser())
            }
            return users
        }

        fun makeUser(): User {
            return User(randomInt(), randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }

    }
}