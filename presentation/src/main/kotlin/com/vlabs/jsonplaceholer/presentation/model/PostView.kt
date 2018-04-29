package com.vlabs.jsonplaceholer.presentation.model

const val POST_ID_KEY = "POST_ID_KEY"

class PostView(val userId: String, val id: String,
                val title: String, val body: String)
