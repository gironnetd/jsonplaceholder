package com.vlabs.jsonplaceholder.domain.interactors.albums

import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.models.Album
import com.vlabs.jsonplaceholder.domain.repositories.albums.AlbumRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetAlbumsByUserId @Inject constructor(val albumRepository: AlbumRepository,
                                                     threadExecutor: ThreadExecutor,
                                                     postExecutionThread: PostExecutionThread):
        SingleUseCase<List<Album>, GetAlbumsByUserId.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Single<List<Album>> {
        return albumRepository.getAlbumsByUserId(params!!.userId)
    }

    class Params private constructor(internal val userId: Int) {
        companion object {

            fun forAlbums(userId: Int): Params {
                return Params(userId)
            }
        }
    }
}

