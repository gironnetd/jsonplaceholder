package com.vlabs.jsonplaceholder.cache.model.users

/**
 * Model used solely for the caching of a bufferroo
 */
data class CachedUser(val userId: Int, val name: String, val userName: String, val email: String,
                      val phone: String, val website: String)