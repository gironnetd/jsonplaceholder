package com.vlabs.jsonplaceholder.data.models

data class CommentEntity(val postId: String, val id: String, val name: String,
                   val email: String, val body: String)