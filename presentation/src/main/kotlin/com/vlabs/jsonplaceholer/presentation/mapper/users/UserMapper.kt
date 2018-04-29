package com.vlabs.jsonplaceholder.presentation.mapper.users

import com.vlabs.jsonplaceholder.domain.models.User
import com.vlabs.jsonplaceholder.presentation.mapper.Mapper
import com.vlabs.jsonplaceholder.presentation.model.UserView
import javax.inject.Inject

/**
 * Map a [UserView] to and from a [User] instance when data is moving between
 * this layer and the Domain layer
 */
open class UserMapper @Inject constructor(): Mapper<UserView, User> {

    /**
     * Map a [User] instance to a [UserView] instance
     */
    override fun mapToView(type: User): UserView {
        return UserView(type.userId ,type.name, type.userName, type.email, type.phone, type.website)
    }
}