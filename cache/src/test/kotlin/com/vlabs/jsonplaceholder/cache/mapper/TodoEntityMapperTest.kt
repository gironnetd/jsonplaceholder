package com.vlabs.jsonplaceholder.cache.mapper

import com.vlabs.jsonplaceholder.cache.model.todos.CachedTodo
import com.vlabs.jsonplaceholder.cache.test.factory.TodoFactory
import com.vlabs.jsonplaceholder.data.mappers.todos.TodoEntityMapper
import com.vlabs.jsonplaceholder.data.models.TodoEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class TodoEntityMapperTest {

    private lateinit var todoEntityMapper: TodoEntityMapper

    @Before
    fun setUp() {
        todoEntityMapper = TodoEntityMapper()
    }

    @Test
    fun mapToCachedMapsData() {
        val todoEntity = TodoFactory.makeTodoEntity()
        val cachedTodo = todoEntityMapper.mapToCached(todoEntity)

        assertTodoDataEquality(todoEntity, cachedTodo)
    }

    @Test
    fun mapFromCachedMapsData() {
        val cachedTodo = TodoFactory.makeCachedTodo()
        val todoEntity = todoEntityMapper.mapFromCached(cachedTodo)

        assertTodoDataEquality(todoEntity, cachedTodo)
    }

    private fun assertTodoDataEquality(todoEntity: TodoEntity,
                                        cachedTodo: CachedTodo) {
        assertEquals(todoEntity.id, cachedTodo.id)
        assertEquals(todoEntity.title, cachedTodo.title)
        assertEquals(todoEntity.userId, cachedTodo.userId)
        assertEquals(todoEntity.completed, cachedTodo.completed)
    }
}