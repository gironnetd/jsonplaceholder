package com.vlabs.jsonplaceholder.instrumentation.test.factory

import com.vlabs.jsonplaceholder.domain.models.Todo
import com.vlabs.jsonplaceholder.instrumentation.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.instrumentation.test.DataFactory.Factory.randomInt

class TodoFactory {

    companion object Factory {

        fun makeTodoList(count: Int): List<Todo> {
            val todos = mutableListOf<Todo>()
            repeat(count) {
                todos.add(makeTodoModel())
            }
            return todos
        }

        fun makeTodoModel(): Todo {
            return Todo(randomInt(), randomInt(), randomUuid(), randomUuid())
        }
    }
}