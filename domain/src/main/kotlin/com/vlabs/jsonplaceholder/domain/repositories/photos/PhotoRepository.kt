package com.vlabs.jsonplaceholder.domain.repositories.photos

import com.vlabs.jsonplaceholder.domain.models.Photo
import io.reactivex.Single

interface PhotoRepository {

    fun getPhotosByAlbumId(albumId: Int): Single<List<Photo>>
}