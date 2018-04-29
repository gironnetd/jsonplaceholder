package com.vlabs.jsonplaceholder.cache.model.photos

data class CachedPhoto(val albumId: String, val id: String,
                       val title: String, val url: String, val thumbnailUrl: String)