package com.vlabs.jsonplaceholder.cache.model.users

/**
 * Model used solely for the caching of a post
 */
data class CachedPost(val userId: String, val id: String,
                val title: String, val body: String)

