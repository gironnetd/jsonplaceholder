package com.vlabs.jsonplaceholder.domain.repositories.albums

import com.vlabs.jsonplaceholder.domain.models.Album
import io.reactivex.Single

interface AlbumRepository {

    fun getAlbumsByUserId(userId: Int) : Single<List<Album>>
}