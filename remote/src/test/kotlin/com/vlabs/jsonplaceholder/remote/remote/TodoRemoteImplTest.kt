package com.vlabs.jsonplaceholder.remote.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.data.models.TodoEntity
import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.todos.TodoEntityMapper
import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.models.TodoModel
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.albums.AlbumService
import com.vlabs.jsonplaceholder.remote.remote.todos.TodoRemoteImpl
import com.vlabs.jsonplaceholder.remote.remote.todos.TodoService
import com.vlabs.jsonplaceholder.remote.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.remote.test.factory.TodoFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TodoRemoteImplTest {

    private lateinit var entityMapper: TodoEntityMapper
    private lateinit var todoService: TodoService

    private lateinit var todoRemoteImpl: TodoRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        todoService = mock()
        todoRemoteImpl = TodoRemoteImpl(todoService, entityMapper)
    }

    //<editor-fold desc="Get Todos">
    @Test
    fun getTodosCompletes() {
        stubTodoServiceGetTodos(Single.just(TodoFactory.makeTodoResponse()))
        val testObserver = todoRemoteImpl.getTodosByUserId(2).test()
        testObserver.assertComplete()
    }

    @Test
    fun getTodosReturnsData() {
        val todoResponse = TodoFactory.makeTodoResponse()
        stubTodoServiceGetTodos(Single.just(todoResponse))
        val todoEntities = mutableListOf<TodoEntity>()
        todoResponse.forEach {
            todoEntities.add(entityMapper.mapFromRemote(it))
        }

        val testObserver = todoRemoteImpl.getTodosByUserId(2).test()
        testObserver.assertValue(todoEntities)
    }
    //</editor-fold>

    private fun stubTodoServiceGetTodos(single: Single<List<TodoModel>>) {
        whenever(todoService.getTodosByUserId(2))
                .thenReturn(single)
    }
}