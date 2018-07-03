package com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.post

import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.interactors.albums.GetAlbumsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.posts.GetPostsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.todos.GetTodosByUserId
import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.models.Post
import com.vlabs.jsonplaceholer.presentation.mapper.albums.AlbumMapper
import com.vlabs.jsonplaceholer.presentation.mapper.posts.PostMapper
import com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.album.AlbumsContract
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class PostsPresenter @Inject constructor(val viewList: PostsContract.View
                                         , val getPostsByUserIdUseCase: SingleUseCase<List<Post>, GetPostsByUserId.Params>, val postMapper: PostMapper): PostsContract.Presenter {

    init {
        viewList.setPresenter(this)
    }

    override fun retrievePosts(userId: Int) {
        getPostsByUserIdUseCase.execute(PostSubscriber(), GetPostsByUserId.Params.forPosts(userId))
    }

    override fun start(userId: Int) {
        retrievePosts(userId)

    }

    override fun start() {
    }

    override fun stop() {
        getPostsByUserIdUseCase.dispose()
    }

    internal fun handleGetPostsSuccess(posts: List<Post>) {
        viewList.hideErrorState()
        if (posts.isNotEmpty()) {
            viewList.hideEmptyState()
            viewList.showPosts(posts.map { postMapper.mapToView(it) })
        } else {
            viewList.hidePosts()
            viewList.showEmptyState()
        }
    }

    inner class PostSubscriber: DisposableSingleObserver<List<Post>>() {

        override fun onSuccess(posts: List<Post>) {
            handleGetPostsSuccess(posts)
        }

        override fun onError(exception: Throwable) {
            //viewList.hideUsers()
            viewList.hideEmptyState()
            viewList.showErrorState()
        }
    }
}