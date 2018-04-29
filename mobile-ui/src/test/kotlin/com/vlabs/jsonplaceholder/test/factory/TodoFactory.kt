package com.vlabs.jsonplaceholder.test.factory

import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomBoolean
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.presentation.test.DataFactory.Factory.randomInt
import com.vlabs.jsonplaceholer.presentation.model.AlbumView
import com.vlabs.jsonplaceholer.presentation.model.TodoView


class TodoFactory {

    companion object Factory {

        fun makeTodoView(): TodoView {
            return TodoView(randomInt(), randomInt(), randomUuid(), randomBoolean())
        }
    }
}