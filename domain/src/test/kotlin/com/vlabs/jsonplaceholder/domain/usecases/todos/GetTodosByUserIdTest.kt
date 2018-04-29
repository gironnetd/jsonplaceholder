package com.vlabs.jsonplaceholder.domain.usecases.todos

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.interactors.albums.GetAlbumsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.todos.GetTodosByUserId
import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.models.Todo
import com.vlabs.jsonplaceholder.domain.repositories.albums.AlbumRepository
import com.vlabs.jsonplaceholder.domain.repositories.todos.TodoRepository
import com.vlabs.jsonplaceholder.domain.test.factory.AlbumFactory
import com.vlabs.jsonplaceholder.domain.test.factory.TodoFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetTodosByUserIdTest {

    private lateinit var getTodosByUserId: GetTodosByUserId

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockTodoRepository: TodoRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockTodoRepository = mock()
        getTodosByUserId = GetTodosByUserId(mockTodoRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getTodosByUserId.buildUseCaseObservable(GetTodosByUserId.Params.forTodos(2))
        verify(mockTodoRepository).getTodosByUserId(2)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubTodoRepositoryGetTodos(Single.just(TodoFactory.makeTodoList(2)))
        val testObserver = getTodosByUserId.buildUseCaseObservable(GetTodosByUserId.Params.forTodos(2)).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val todos = TodoFactory.makeTodoList(2)
        stubTodoRepositoryGetTodos(Single.just(todos))
        val testObserver = getTodosByUserId.buildUseCaseObservable(GetTodosByUserId.Params.forTodos(2)).test()
        testObserver.assertValue(todos)
    }

    private fun stubTodoRepositoryGetTodos(single: Single<List<Todo>>) {
        whenever(mockTodoRepository.getTodosByUserId(2))
                .thenReturn(single)
    }
}