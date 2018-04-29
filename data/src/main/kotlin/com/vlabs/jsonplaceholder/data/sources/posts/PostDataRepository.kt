package com.vlabs.jsonplaceholder.data.sources.posts

import com.vlabs.jsonplaceholder.data.mappers.posts.PostMapper
import com.vlabs.jsonplaceholder.data.models.PostEntity
import com.vlabs.jsonplaceholder.domain.models.Post
import com.vlabs.jsonplaceholder.domain.repositories.posts.PostRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [BufferooRepository] interface for communicating to and from
 * data sources
 */
class PostDataRepository @Inject constructor(private val factory: PostDataStoreFactory,
                                             private val postMapper: PostMapper) :
        PostRepository {

    override fun getPostsByUserId(userId: Int): Single<List<Post>> {
        val dataStore = factory.retrieveDataStore(userId)
        return dataStore.getPostsByUserId(userId)
                .flatMap {
                    if (dataStore is PostRemoteDataStore) {
                        savePostEntities(it).toSingle { it }
                    } else {
                        Single.just(it)
                    }
                }
                .map { list ->
                    list.map { listItem ->
                        postMapper.mapFromEntity(listItem)
                    }
                }    }

    override fun clearPosts(): Completable {
        return factory.retrieveCacheDataStore().clearPosts()
    }

    override fun savePosts(posts: List<Post>): Completable {
        val postEntities = posts.map { postMapper.mapToEntity(it) }
        return savePostEntities(postEntities)
    }

    private fun savePostEntities(posts: List<PostEntity>): Completable {
        return factory.retrieveCacheDataStore().savePosts(posts)
    }

//    override fun getPosts(): Single<List<Post>> {
//        val dataStore = factory.retrieveDataStore()
//        return dataStore.getPosts()
//                .flatMap {
//                    if (dataStore is PostRemoteDataStore) {
//                        savePostEntities(it).toSingle { it }
//                    } else {
//                        Single.just(it)
//                    }
//                }
//                .map { list ->
//                    list.map { listItem ->
//                        postMapper.mapFromEntity(listItem)
//                    }
//                }
//    }

}