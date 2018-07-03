package com.vlabs.jsonplaceholder.ui.userdetails.fragments.todo

import com.vlabs.jsonplaceholder.domain.interactors.todos.GetTodosByUserId
import com.vlabs.jsonplaceholder.injection.scopes.PerActivity
import com.vlabs.jsonplaceholder.injection.scopes.PerFragment
import com.vlabs.jsonplaceholer.presentation.mapper.todos.TodoMapper
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.todo.TodosContract
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.todo.TodosPresenter
import dagger.Module
import dagger.Provides

@Module
class TodoFragmentModule {

    //@PerFragment
    @Provides
    internal fun provideTodosView(todosFragment: TodoFragment): TodosContract.View {
        return todosFragment
    }

    //@PerFragment
    @Provides
    internal fun provideTodosPresenter(mainView: TodosContract.View,
                                       getTodosByUserId: GetTodosByUserId
                                       , todoMapper: TodoMapper): TodosContract.Presenter {
        return TodosPresenter(mainView, getTodosByUserId, todoMapper)
    }
}