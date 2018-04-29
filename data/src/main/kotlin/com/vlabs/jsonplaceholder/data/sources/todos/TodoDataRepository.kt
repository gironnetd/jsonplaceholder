package com.vlabs.jsonplaceholder.data.sources.todos

import com.vlabs.jsonplaceholder.data.mappers.todos.TodoMapper
import com.vlabs.jsonplaceholder.data.models.TodoEntity
import com.vlabs.jsonplaceholder.domain.models.Todo
import com.vlabs.jsonplaceholder.domain.repositories.todos.TodoRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [BufferooRepository] interface for communicating to and from
 * data sources
 */
class TodoDataRepository @Inject constructor(private val factory: TodoDataStoreFactory,
                                             private val todoMapper: TodoMapper) :
        TodoRepository {


//    override fun clearTodos(): Completable {
//        return factory.retrieveCacheDataStore().clearTodos()
//    }
//
//    override fun saveTodos(posts: List<Todo>): Completable {
//        val postEntities = posts.map { todoMapper.mapToEntity(it) }
//        return saveTodoEntities(postEntities)
//    }

    private fun saveTodoEntities(posts: List<TodoEntity>): Completable {
        return factory.retrieveCacheDataStore().saveTodos(posts)
    }

    override fun getTodosByUserId(userId: Int): Single<List<Todo>> {
        val dataStore = factory.retrieveDataStore(userId)
        return dataStore.getTodosByUserId(userId)
                .flatMap {
                    if (dataStore is TodoRemoteDataStore) {
                        saveTodoEntities(it).toSingle { it }
                    } else {
                        Single.just(it)
                    }
                }
                .map { list ->
                    list.map { listItem ->
                        todoMapper.mapFromEntity(listItem)
                    }
                }
    }

}