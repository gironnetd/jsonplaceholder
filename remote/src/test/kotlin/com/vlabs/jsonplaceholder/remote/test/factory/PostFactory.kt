package com.vlabs.jsonplaceholder.remote.test.factory

import com.vlabs.jsonplaceholder.remote.models.AlbumModel
import com.vlabs.jsonplaceholder.remote.models.PostModel
import com.vlabs.jsonplaceholder.remote.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.remote.test.DataFactory.Factory.randomInt

class PostFactory {

companion object Factory {

    fun makePostResponse(): List<PostModel> {
        val postResponse = makePostModelList(5)
        return postResponse
    }

    fun makePostModelList(count: Int): List<PostModel> {
        val postEntities = mutableListOf<PostModel>()
        repeat(count) {
            postEntities.add(makePostModel())
        }
        return postEntities
    }

    fun makePostModel(): PostModel {
        return PostModel(randomUuid(), randomUuid(), randomUuid(), randomUuid())
    }

}
}