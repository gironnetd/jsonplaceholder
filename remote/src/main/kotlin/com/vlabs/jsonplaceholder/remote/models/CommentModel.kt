package com.vlabs.jsonplaceholder.remote.models

data class CommentModel(val postId: String, val id: String, val name: String,
                         val email: String, val body: String)