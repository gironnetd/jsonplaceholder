package com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.todo

import com.vlabs.jsonplaceholder.presentation.BasePresenter
import com.vlabs.jsonplaceholder.presentation.BaseView
import com.vlabs.jsonplaceholer.presentation.model.TodoView

interface TodosContract {

    interface View : BaseView<Presenter> {

        fun showTodos(todos: List<TodoView>)

        fun hideTodos()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()
    }

    interface Presenter : BasePresenter {

        fun start(userId: Int)

        fun retrieveTodos(userId: Int)
    }
}