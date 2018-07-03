package com.vlabs.jsonplaceholer.presentation.ui.userdetails

import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.interactors.albums.GetAlbumsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.posts.GetPostsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.todos.GetTodosByUserId
import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.models.Post
import com.vlabs.jsonplaceholder.domain.models.Todo
import com.vlabs.jsonplaceholder.domain.models.User
import com.vlabs.jsonplaceholer.presentation.mapper.albums.AlbumMapper
import com.vlabs.jsonplaceholer.presentation.mapper.posts.PostMapper
import com.vlabs.jsonplaceholer.presentation.mapper.todos.TodoMapper
import com.vlabs.jsonplaceholer.presentation.model.AlbumView
import com.vlabs.jsonplaceholer.presentation.model.PostView
import com.vlabs.jsonplaceholer.presentation.model.TodoView
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class UsersDetailsPresenter
@Inject constructor(val viewList: UserDetailsContract.View
                    /*, val getAlbumsByUserIdUseCase: SingleUseCase<List<Album>, GetAlbumsByUserId.Params>
                    , val albumMapper: AlbumMapper
                    , val getPostsByUserIdUseCase: SingleUseCase<List<Post>, GetPostsByUserId.Params>
                    , val postMapper: PostMapper
                    , val getTodosByUserIdUseCase: SingleUseCase<List<Todo>, GetTodosByUserId.Params>
                    , val todoMapper: TodoMapper*/):
        UserDetailsContract.Presenter  {


    override fun start(userId: Int) {
        //retrieveAlbums(userId)
        //retrievePosts(userId)
        //retrieveTodos(userId)
    }

    init {
        viewList.setPresenter(this)
    }

    //lateinit var albums: List<AlbumView>
    lateinit var posts: List<PostView>
    lateinit var todos: List<TodoView>

//    override fun retrieveAlbums(userId: Int) {
//        getAlbumsByUserIdUseCase.execute(AlbumSubscriber(), GetAlbumsByUserId.Params.forAlbums(userId))
//    }

//    override fun retrievePosts(userId: Int) {
//      //  getPostsByUserIdUseCase.execute(PostSubscriber(), GetPostsByUserId.Params.forPosts(userId))
//    }
//
//    override fun retrieveTodos(userId: Int) {
//      //  getTodosByUserIdUseCase.execute(TodoSubscriber(), GetTodosByUserId.Params.forTodos(userId))
//    }



    override fun start() {

    }

    override fun stop() {
        //getAlbumsByUserIdUseCase.dispose()
        //getPostsByUserIdUseCase.dispose()
        //getTodosByUserIdUseCase.dispose()
    }

//    internal fun handleGetAlbumsSuccess(albums: List<Album>) {
//        viewList.hideErrorState()
//        if (albums.isNotEmpty()) {
//            viewList.hideEmptyState()
//            viewList.showAlbums(albums.map { albumMapper.mapToView(it) })
//        } else {
//            viewList.hideAlbums()
//            viewList.showEmptyState()
//        }
//    }
//
//    inner class AlbumSubscriber: DisposableSingleObserver<List<Album>>() {
//
//        override fun onSuccess(albums: List<Album>) {
//            handleGetAlbumsSuccess(albums)
//        }
//
//        override fun onError(exception: Throwable) {
//            //viewList.hideUsers()
//            viewList.hideEmptyState()
//            viewList.showErrorState()
//        }
//
//    }

//    internal fun handleGetPostsSuccess(posts: List<Post>) {
//        viewList.hideErrorState()
//        if (posts.isNotEmpty()) {
//            viewList.hideEmptyState()
//            viewList.showPosts(posts.map { postMapper.mapToView(it) })
//        } else {
//            viewList.hidePosts()
//            viewList.showEmptyState()
//        }
//    }
//
//    inner class PostSubscriber: DisposableSingleObserver<List<Post>>() {
//
//        override fun onSuccess(posts: List<Post>) {
//            handleGetPostsSuccess(posts)
//        }
//
//        override fun onError(exception: Throwable) {
//            //viewList.hideUsers()
//            viewList.hideEmptyState()
//            viewList.showErrorState()
//        }
//    }
//
//    internal fun handleGetTodosSuccess(todos: List<Todo>) {
//        viewList.hideErrorState()
//        if (todos.isNotEmpty()) {
//            viewList.hideEmptyState()
//            viewList.showTodos(todos.map { todoMapper.mapToView(it) })
//        } else {
//            viewList.hideTodos()
//            viewList.showEmptyState()
//        }
//    }
//
//    inner class TodoSubscriber: DisposableSingleObserver<List<Todo>>() {
//
//        override fun onSuccess(todos: List<Todo>) {
//            handleGetTodosSuccess(todos)
//        }
//
//        override fun onError(exception: Throwable) {
//            //viewList.hideUsers()
//            viewList.hideEmptyState()
//            viewList.showErrorState()
//        }
//
//    }
}