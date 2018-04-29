package com.vlabs.jsonplaceholder.remote.models

import com.squareup.moshi.Json

//data class PostModel(val userId: String, val id: String, val title: String, val body: String)

data class PostModel(@Json(name = "userId") val userId: String,
                     @Json(name = "id") val id: String,
                     @Json(name = "title") val title: String,
                     @Json(name = "body") val body: String)
