package com.vlabs.jsonplaceholder.remote.test.factory

import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.models.TodoModel
import com.vlabs.jsonplaceholder.remote.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.remote.test.DataFactory.Factory.randomInt

class TodoFactory {

    companion object Factory {

        fun makeTodoResponse(): List<TodoModel> {
            val todoResponse = makeTodoModelList(5)
            return todoResponse
        }

        fun makeTodoModelList(count: Int): List<TodoModel> {
            val todoEntities = mutableListOf<TodoModel>()
            repeat(count) {
                todoEntities.add(makeTodoModel())
            }
            return todoEntities
        }

        fun makeTodoModel(): TodoModel {
            return TodoModel(randomInt(), randomInt(), randomUuid(), randomUuid())
        }

    }
}