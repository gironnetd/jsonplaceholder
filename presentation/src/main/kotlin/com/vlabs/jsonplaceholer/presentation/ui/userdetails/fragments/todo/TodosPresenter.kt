package com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.todo

import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.interactors.albums.GetAlbumsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.posts.GetPostsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.todos.GetTodosByUserId
import com.vlabs.jsonplaceholder.domain.models.Post
import com.vlabs.jsonplaceholder.domain.models.Todo
import com.vlabs.jsonplaceholer.presentation.mapper.posts.PostMapper
import com.vlabs.jsonplaceholer.presentation.mapper.todos.TodoMapper
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.post.PostsContract
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class TodosPresenter @Inject constructor(val viewList: TodosContract.View
                                         , val getTodosByUserIdUseCase: SingleUseCase<List<Todo>, GetTodosByUserId.Params>, val todoMapper: TodoMapper): TodosContract.Presenter {

    init {
        viewList.setPresenter(this)
    }

    override fun retrieveTodos(userId: Int) {
        getTodosByUserIdUseCase.execute(TodoSubscriber(), GetTodosByUserId.Params.forTodos(userId))
    }

    override fun start() {
        retrieveTodos(1)
    }

    override fun stop() {
        getTodosByUserIdUseCase.dispose()
    }

    internal fun handleGetTodosSuccess(todos: List<Todo>) {
        viewList.hideErrorState()
        if (todos.isNotEmpty()) {
            viewList.hideEmptyState()
            viewList.showTodos(todos.map { todoMapper.mapToView(it) })
        } else {
            viewList.hideTodos()
            viewList.showEmptyState()
        }
    }

    inner class TodoSubscriber: DisposableSingleObserver<List<Todo>>() {

        override fun onSuccess(todos: List<Todo>) {
            handleGetTodosSuccess(todos)
        }

        override fun onError(exception: Throwable) {
            //viewList.hideUsers()
            viewList.hideEmptyState()
            viewList.showErrorState()
        }
    }
}