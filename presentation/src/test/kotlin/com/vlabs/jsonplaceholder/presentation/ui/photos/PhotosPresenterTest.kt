package com.vlabs.jsonplaceholder.presentation.ui.photos

import com.nhaarman.mockito_kotlin.*
import com.vlabs.jsonplaceholder.domain.interactors.comments.GetCommentsByPostId
import com.vlabs.jsonplaceholder.domain.interactors.photos.GetPhotosByAlbumId
import com.vlabs.jsonplaceholder.domain.models.Comment
import com.vlabs.jsonplaceholder.domain.models.Photo
import com.vlabs.jsonplaceholder.presentation.test.factory.CommentFactory
import com.vlabs.jsonplaceholder.presentation.test.factory.PhotoFactory
import com.vlabs.jsonplaceholer.presentation.mapper.comments.CommentMapper
import com.vlabs.jsonplaceholer.presentation.mapper.photos.PhotoMapper
import com.vlabs.jsonplaceholer.presentation.ui.comments.CommentsContract
import com.vlabs.jsonplaceholer.presentation.ui.comments.CommentsPresenter
import com.vlabs.jsonplaceholer.presentation.ui.photos.PhotosContract
import com.vlabs.jsonplaceholer.presentation.ui.photos.PhotosPresenter
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PhotosPresenterTest {

    private lateinit var mockPhotosListView: PhotosContract.View
    private lateinit var mockPhotoMapper: PhotoMapper
    private lateinit var mockGetPhotosByAlbumId: GetPhotosByAlbumId

    private lateinit var photosListPresenter: PhotosPresenter
    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<Photo>>>

    @Before
    fun setup() {
        captor = argumentCaptor<DisposableSingleObserver<List<Photo>>>()
        mockPhotosListView = mock()
        mockPhotoMapper = mock()
        mockGetPhotosByAlbumId = mock()
        photosListPresenter = PhotosPresenter(mockPhotosListView,
                mockGetPhotosByAlbumId, mockPhotoMapper)
    }

    //<editor-fold desc="Retrieve Photos">
    @Test
    fun retrievePhotosHidesErrorState() {
        photosListPresenter.retrievePhotos(2)

        verify(mockGetPhotosByAlbumId).execute(captor.capture(), eq(GetPhotosByAlbumId.Params.forPhotos(2)))
        captor.firstValue.onSuccess(PhotoFactory.makePhotoList(2))
        verify(mockPhotosListView).hideErrorState()
    }

    @Test
    fun retrievePhotosHidesEmptyState() {
        photosListPresenter.retrievePhotos(2)

        verify(mockGetPhotosByAlbumId).execute(captor.capture(), eq(GetPhotosByAlbumId.Params.forPhotos(2)))
        captor.firstValue.onSuccess(PhotoFactory.makePhotoList(2))
        verify(mockPhotosListView).hideEmptyState()
    }

    @Test
    fun retrievePhotosShowsPhotos() {
        val photos = PhotoFactory.makePhotoList(2)
        photosListPresenter.retrievePhotos(2)

        verify(mockGetPhotosByAlbumId).execute(captor.capture(), eq(GetPhotosByAlbumId.Params.forPhotos(2)))
        captor.firstValue.onSuccess(photos)
        verify(mockPhotosListView).showPhotos(
                photos.map { mockPhotoMapper.mapToView(it) })
    }

    @Test
    fun retrievePhotosShowsEmptyState() {
        photosListPresenter.retrievePhotos(2)

        verify(mockGetPhotosByAlbumId).execute(captor.capture(), eq(GetPhotosByAlbumId.Params.forPhotos(2)))
        captor.firstValue.onSuccess(PhotoFactory.makePhotoList(0))
        verify(mockPhotosListView).showEmptyState()
    }

    @Test
    fun retrievePhotosHidesPhotos() {
        photosListPresenter.retrievePhotos(2)

        verify(mockGetPhotosByAlbumId).execute(captor.capture(), eq(GetPhotosByAlbumId.Params.forPhotos(2)))
        captor.firstValue.onSuccess(PhotoFactory.makePhotoList(0))
        verify(mockPhotosListView).hidePhotos()
    }

    @Test
    fun retrievePhotosShowsErrorState() {
        photosListPresenter.retrievePhotos(2)

        verify(mockGetPhotosByAlbumId).execute(captor.capture(), eq(GetPhotosByAlbumId.Params.forPhotos(2)))
        captor.firstValue.onError(RuntimeException())
        verify(mockPhotosListView).showErrorState()
    }

    @Test
    fun retrievePhotosHidesPhotosWhenErrorThrown() {
        photosListPresenter.retrievePhotos(2)

        verify(mockGetPhotosByAlbumId).execute(captor.capture(), eq(GetPhotosByAlbumId.Params.forPhotos(2)))
        captor.firstValue.onError(RuntimeException())
        verify(mockPhotosListView).hidePhotos()
    }

    @Test
    fun retrievePhotosHidesEmptyStateWhenErrorThrown() {
        photosListPresenter.retrievePhotos(2)

        verify(mockGetPhotosByAlbumId).execute(captor.capture(), eq(GetPhotosByAlbumId.Params.forPhotos(2)))
        captor.firstValue.onError(RuntimeException())
        verify(mockPhotosListView).hideEmptyState()
    }

}