package com.vlabs.jsonplaceholder.test.factory

import com.vlabs.jsonplaceholder.presentation.model.UserView
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomInt
import com.vlabs.jsonplaceholer.presentation.model.AlbumView

class UserFactory {

    companion object Factory {

        fun makeUserView(): UserView {
            return UserView(randomInt(), randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid())
        }
    }
}