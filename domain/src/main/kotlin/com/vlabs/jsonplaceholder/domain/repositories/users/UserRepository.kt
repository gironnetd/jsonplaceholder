package com.vlabs.jsonplaceholder.domain.repositories.users

import com.vlabs.jsonplaceholder.domain.models.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {

    fun clearUsers(): Completable

    fun saveUsers(users: List<User>): Completable

    fun getUsers(): Single<List<User>>
}