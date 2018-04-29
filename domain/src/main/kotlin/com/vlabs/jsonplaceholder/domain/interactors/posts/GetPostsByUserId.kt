package com.vlabs.jsonplaceholder.domain.interactors.posts

import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.models.Post
import com.vlabs.jsonplaceholder.domain.repositories.posts.PostRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetPostsByUserId @Inject constructor(val postRepository: PostRepository,
                                                 threadExecutor: ThreadExecutor,
                                                 postExecutionThread: PostExecutionThread):
        SingleUseCase<List<Post>, GetPostsByUserId.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Single<List<Post>> {
        return postRepository.getPostsByUserId(params!!.userId)
    }

    class Params private constructor(internal val userId: Int) {
        companion object {

            fun forPosts(userId: Int): Params {
                return Params(userId)
            }
        }
    }
}

