package com.vlabs.jsonplaceholder.cache.model.comments

data class CachedComment(val postId: String, val id: String, val name: String,
                         val email: String, val body: String)