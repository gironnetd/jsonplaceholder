package com.vlabs.jsonplaceholder.mappers

import com.vlabs.jsonplaceholder.presentation.model.UserView
import com.vlabs.jsonplaceholder.model.UserViewModel
import javax.inject.Inject

/**
 * Map a [UserView] to and from a [UserViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class UserMapper @Inject constructor(): Mapper<UserViewModel, UserView> {

    /**
     * Map a [UserView] instance to a [UserViewModel] instance
     */
    override fun mapToViewModel(type: UserView): UserViewModel {
        return UserViewModel(type.userId, type.name, type.userName, type.email, type.phone, type.website)
    }
}