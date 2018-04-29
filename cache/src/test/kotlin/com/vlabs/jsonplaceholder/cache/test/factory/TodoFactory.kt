package com.vlabs.jsonplaceholder.cache.test.factory

import com.vlabs.jsonplaceholder.cache.model.comments.CachedComment
import com.vlabs.jsonplaceholder.cache.model.todos.CachedTodo
import com.vlabs.jsonplaceholder.data.models.CommentEntity
import com.vlabs.jsonplaceholder.cache.test.factory.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.cache.test.factory.DataFactory.Factory.randomInt
import com.vlabs.jsonplaceholder.data.models.TodoEntity

class TodoFactory {

    companion object Factory {

        fun makeCachedTodo(): CachedTodo {
            return CachedTodo(randomInt(), randomInt(), randomUuid(), randomUuid())
        }

        fun makeTodoEntity(): TodoEntity {
            return TodoEntity(randomInt(), randomInt(), randomUuid(), randomUuid())
        }

        fun makeTodoEntityList(count: Int): List<TodoEntity> {
            val bufferooEntities = mutableListOf<TodoEntity>()
            repeat(count) {
                bufferooEntities.add(makeTodoEntity())
            }
            return bufferooEntities
        }

        fun makeCachedTodoList(count: Int): List<CachedTodo> {
            val cachedTodos = mutableListOf<CachedTodo>()
            repeat(count) {
                cachedTodos.add(makeCachedTodo())
            }
            return cachedTodos
        }

    }

}