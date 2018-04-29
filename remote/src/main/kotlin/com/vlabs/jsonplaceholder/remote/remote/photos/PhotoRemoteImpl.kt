package com.vlabs.jsonplaceholder.remote.remote.photos

import com.vlabs.jsonplaceholder.data.models.PhotoEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.repositories.photos.PhotoRemote
import com.vlabs.jsonplaceholder.data.repositories.posts.PostRemote
import com.vlabs.jsonplaceholder.remote.mappers.photos.PhotoEntityMapper
import com.vlabs.jsonplaceholder.remote.mappers.posts.PostEntityMapper
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [PostRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
@Singleton
class PhotoRemoteImpl @Inject constructor(private val photoService: PhotoService,
                                          private val entityMapper: PhotoEntityMapper) :
        PhotoRemote {

    override fun getPhotosByAlbumId(albumId: Int): Single<List<PhotoEntity>> {
        return photoService.getPhotosByAlbumId(albumId)
                .map {
                    //it.posts.map { listItem ->
                    entityMapper.mapFromRemote(it)
                    //}
                }
    }

    /**
     * Retrieve a list of [PostEntity] instances from the [PhotoService].
     */

//    override fun getPosts(): Single<List<PostEntity>> {
//        //val p = photoService.getPosts()
//        return photoService.getPosts()
//                .map {
//                    //it.posts.map { listItem ->
//                        entityMapper.mapFromRemote(it)
//                    //}
//                }
//    }

}