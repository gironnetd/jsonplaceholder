package com.vlabs.jsonplaceholder.presentation.model

/**
 * Representation for a [BufferooView] instance for this layers Model representation
 */
const val USER_ID_KEY = "USER_ID_KEY"

class UserView(val userId: Int, val name: String, val userName: String,
                val email: String, val phone: String, val website: String
)