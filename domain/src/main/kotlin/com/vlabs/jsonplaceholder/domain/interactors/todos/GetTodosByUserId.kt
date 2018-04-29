package com.vlabs.jsonplaceholder.domain.interactors.todos

import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.models.Todo
import com.vlabs.jsonplaceholder.domain.repositories.todos.TodoRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetTodosByUserId @Inject constructor(val todoRepository: TodoRepository,
                                                threadExecutor: ThreadExecutor,
                                                postExecutionThread: PostExecutionThread):
        SingleUseCase<List<Todo>, GetTodosByUserId.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Single<List<Todo>> {
        return todoRepository.getTodosByUserId(params!!.userId)
    }

    class Params private constructor(internal val userId: Int) {
        companion object {

            fun forTodos(userId: Int): Params {
                return Params(userId)
            }
        }
    }
}

