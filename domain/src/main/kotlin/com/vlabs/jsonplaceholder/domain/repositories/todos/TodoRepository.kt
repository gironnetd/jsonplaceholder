package com.vlabs.jsonplaceholder.domain.repositories.todos

import com.vlabs.jsonplaceholder.domain.models.Todo
import io.reactivex.Single

interface TodoRepository {

    fun getTodosByUserId(userId: Int): Single<List<Todo>>
}