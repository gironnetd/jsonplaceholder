package com.vlabs.jsonplaceholder.domain.interactors.users

import com.vlabs.jsonplaceholder.domain.interactor.SingleUseCase
import com.vlabs.jsonplaceholder.domain.models.User
import com.vlabs.jsonplaceholder.domain.repositories.users.UserRepository
import io.reactivex.Single
import com.vlabs.jsonplaceholder.domain.executor.PostExecutionThread
import com.vlabs.jsonplaceholder.domain.executor.ThreadExecutor
import javax.inject.Inject

open class GetUsers @Inject constructor(val userRepository: UserRepository,
                                                threadExecutor: ThreadExecutor,
                                                postExecutionThread: PostExecutionThread):
        SingleUseCase<List<User>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Single<List<User>> {
        return userRepository.getUsers()
    }
}

