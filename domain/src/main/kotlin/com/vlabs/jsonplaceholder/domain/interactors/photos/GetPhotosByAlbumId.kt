package com.vlabs.jsonplaceholder.domain.interactors.photos

import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.models.Photo
import com.vlabs.jsonplaceholder.domain.repositories.photos.PhotoRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetPhotosByAlbumId @Inject constructor(val photoRepository: PhotoRepository,
                                                 threadExecutor: ThreadExecutor,
                                                 postExecutionThread: PostExecutionThread):
        SingleUseCase<List<Photo>, GetPhotosByAlbumId.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Single<List<Photo>> {
        return photoRepository.getPhotosByAlbumId(params!!.albumId)
    }

    class Params private constructor(internal val albumId: Int) {
        companion object {

            fun forPhotos(albumId: Int): Params {
                return Params(albumId)
            }
        }
    }
}