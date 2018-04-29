package com.vlabs.jsonplaceholder.remote.remote.albums

import com.vlabs.jsonplaceholder.data.models.AlbumEntity
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.data.repositories.albums.AlbumRemote
import com.vlabs.jsonplaceholder.data.repositories.posts.PostRemote
import com.vlabs.jsonplaceholder.remote.mappers.albums.AlbumEntityMapper
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
class AlbumRemoteImpl @Inject constructor(private val albumService: AlbumService,
                                          private val entityMapper: AlbumEntityMapper) : AlbumRemote {


    override fun getAlbumsByUserId(userId: Int): Single<List<AlbumEntity>> {
        //val p = albumService.getPosts()
        return albumService.getAlbumsByUserId(userId)
                .map {
                    //it.posts.map { listItem ->
                    entityMapper.mapFromRemote(it)
                    //}
                }
    }


    /**
     * Retrieve a list of [PostEntity] instances from the [AlbumService].
     */

//    override fun getPosts(): Single<List<AlbumEntity>> {
//        //val p = albumService.getPosts()
//        return albumService.getAlbumsByUserId()
//                .map {
//                    //it.posts.map { listItem ->
//                        entityMapper.mapFromRemote(it)
//                    //}
//                }
//    }

}