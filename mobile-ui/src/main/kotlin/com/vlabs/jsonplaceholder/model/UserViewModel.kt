package com.vlabs.jsonplaceholder.model

/**
 * Representation for a [UserViewModel] fetched from an external layer data source
 */
class UserViewModel(val userId: Int, val name: String, val userName: String,
               val email: String, val phone: String, val website: String
)