package com.vlabs.jsonplaceholer.presentation.ui.userdetails.fragments.album

import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.interactors.albums.GetAlbumsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.posts.GetPostsByUserId
import com.vlabs.jsonplaceholder.domain.interactors.todos.GetTodosByUserId
import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholer.presentation.mapper.albums.AlbumMapper
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class AlbumsPresenter @Inject constructor(val viewList: AlbumsContract.View
                                              , val getAlbumsByUserIdUseCase: SingleUseCase<List<Album>, GetAlbumsByUserId.Params>, val albumMapper: AlbumMapper):
        AlbumsContract.Presenter {

    init {
        viewList.setPresenter(this)
    }

    override fun retrieveAlbums(userId: Int) {
        getAlbumsByUserIdUseCase.execute(AlbumSubscriber(), GetAlbumsByUserId.Params.forAlbums(userId))
    }

    override fun start(userId: Int) {
        retrieveAlbums(userId)
    }

    override fun start() {
       // retrieveAlbums(userId)
    }

    override fun stop() {
        getAlbumsByUserIdUseCase.dispose()
    }

    internal fun handleGetAlbumsSuccess(albums: List<Album>) {
        viewList.hideErrorState()
        if (albums.isNotEmpty()) {
            viewList.hideEmptyState()
            viewList.showAlbums(albums.map { albumMapper.mapToView(it) })
        } else {
            viewList.hideAlbums()
            viewList.showEmptyState()
        }
    }

    inner class AlbumSubscriber: DisposableSingleObserver<List<Album>>() {

        override fun onSuccess(albums: List<Album>) {
            handleGetAlbumsSuccess(albums)
        }

        override fun onError(exception: Throwable) {
            //viewList.hideUsers()
            viewList.hideEmptyState()
            viewList.showErrorState()
        }

    }
}