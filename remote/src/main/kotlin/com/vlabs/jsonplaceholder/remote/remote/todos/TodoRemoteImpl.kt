package com.vlabs.jsonplaceholder.remote.remote.todos

import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.models.TodoEntity
import com.vlabs.jsonplaceholder.data.repositories.posts.PostRemote
import com.vlabs.jsonplaceholder.data.repositories.todos.TodoRemote
import com.vlabs.jsonplaceholder.remote.mappers.posts.PostEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.todos.TodoEntityMapper
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [PostRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
@Singleton
class TodoRemoteImpl @Inject constructor(private val todoService: TodoService,
                                         private val entityMapper: TodoEntityMapper) :
        TodoRemote {

    override fun getTodosByUserId(userId: Int): Single<List<TodoEntity>> {
        return todoService.getTodosByUserId(userId)
                .map {
                    //it.posts.map { listItem ->
                    entityMapper.mapFromRemote(it)
                    //}
                }
    }


    /**
     * Retrieve a list of [PostEntity] instances from the [TodoService].
     */

//    override fun getPosts(): Single<List<TodoEntity>> {
//        //val p = todoService.getPosts()
//        return todoService.getPosts()
//                .map {
//                    //it.posts.map { listItem ->
//                        entityMapper.mapFromRemote(it)
//                    //}
//                }
//    }

}