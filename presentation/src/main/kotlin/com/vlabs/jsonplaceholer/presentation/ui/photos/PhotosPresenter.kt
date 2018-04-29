package com.vlabs.jsonplaceholer.presentation.ui.photos

import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.interactors.photos.GetPhotosByAlbumId
import com.vlabs.jsonplaceholder.domain.models.Photo
import com.vlabs.jsonplaceholer.presentation.mapper.photos.PhotoMapper
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class PhotosPresenter @Inject constructor(val viewList: PhotosContract.View,
                                          val getPhotosByAlbumIdUseCase: SingleUseCase<List<Photo>, GetPhotosByAlbumId.Params>,
                                          val userMapper: PhotoMapper): PhotosContract.Presenter {

    init {
        viewList.setPresenter(this)
    }

    override fun start(albumId: Int) {
        retrievePhotos(albumId)
    }

    override fun start() {
        retrievePhotos(1)
    }

    override fun stop() {
        getPhotosByAlbumIdUseCase.dispose()
    }

    override fun retrievePhotos(albumId: Int) {
        getPhotosByAlbumIdUseCase.execute(PhotoSubscriber(), GetPhotosByAlbumId.Params.forPhotos(albumId))
    }

    internal fun handleGetPhotosSuccess(users: List<Photo>) {
        viewList.hideErrorState()
        if (users.isNotEmpty()) {
            viewList.hideEmptyState()
            viewList.showPhotos(users.map { userMapper.mapToView(it) })
        } else {
            viewList.hidePhotos()
            viewList.showEmptyState()
        }
    }

    inner class PhotoSubscriber: DisposableSingleObserver<List<Photo>>() {

        override fun onSuccess(t: List<Photo>) {
            handleGetPhotosSuccess(t)
        }

        override fun onError(exception: Throwable) {
            viewList.hidePhotos()
            viewList.hideEmptyState()
            viewList.showErrorState()
        }
    }
}