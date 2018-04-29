package com.vlabs.jsonplaceholder.mappers

import com.vlabs.jsonplaceholder.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.test.factory.TodoFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class TodoMapperTest {

    private lateinit var todoMapper: TodoMapper

    @Before
    fun setUp() {
        todoMapper = TodoMapper()
    }

    @Test
    fun mapToViewMapsData() {
        val todoView = TodoFactory.makeTodoView()
        val todoViewModel = todoMapper.mapToViewModel(todoView)

        assertEquals(todoView.id, todoViewModel.id)
        assertEquals(todoView.title, todoViewModel.title)
        assertEquals(todoView.userId, todoViewModel.userId)
        assertEquals(todoView.completed, todoViewModel.completed)
    }
}