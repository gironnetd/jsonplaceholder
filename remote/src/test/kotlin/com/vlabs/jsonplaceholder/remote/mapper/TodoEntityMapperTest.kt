package com.vlabs.jsonplaceholder.remote.mapper

import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.todos.TodoEntityMapper
import com.vlabs.jsonplaceholder.remote.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.remote.test.factory.TodoFactory
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
    fun mapFromRemoteMapsData() {
        val todoModel = TodoFactory.makeTodoModel()
        val todoEntity = todoEntityMapper.mapFromRemote(todoModel)

        assertEquals(todoModel.id, todoEntity.id)
        assertEquals(todoModel.title, todoEntity.title)
        assertEquals(todoModel.userId, todoEntity.userId)
        assertEquals(todoModel.completed, todoEntity.completed)
    }
}