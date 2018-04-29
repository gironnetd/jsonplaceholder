package com.vlabs.jsonplaceholder.domain.test.factory

import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholder.domain.models.Todo
import com.vlabs.jsonplaceholder.domain.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.domain.test.DataFactory.Factory.randomInt

class TodoFactory {

    companion object Factory {

        fun makeTodoList(count: Int): List<Todo> {
            val todos = mutableListOf<Todo>()
            repeat(count) {
                todos.add(makeTodo())
            }
            return todos
        }

        fun makeTodo(): Todo {
            return Todo(randomInt(), randomInt(), randomUuid(), randomUuid())
        }

    }
}