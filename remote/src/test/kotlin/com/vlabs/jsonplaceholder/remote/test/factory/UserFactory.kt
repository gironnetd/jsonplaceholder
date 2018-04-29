package com.vlabs.jsonplaceholder.remote.test.factory

import com.vlabs.jsonplaceholder.remote.models.AddressModel
import com.vlabs.jsonplaceholder.remote.models.CompanyModel
import com.vlabs.jsonplaceholder.remote.models.GeoModel
import com.vlabs.jsonplaceholder.remote.models.UserModel
import com.vlabs.jsonplaceholder.remote.test.DataFactory.Factory.randomUuid
import com.vlabs.jsonplaceholder.remote.test.DataFactory.Factory.randomInt

class UserFactory {

    companion object Factory {

        fun makeUserResponse(): List<UserModel> {
            val userResponse = makeUserModelList(5)
            return userResponse
        }

        fun makeUserModelList(count: Int): List<UserModel> {
            val userEntities = mutableListOf<UserModel>()
            repeat(count) {
                userEntities.add(makeUserModel())
            }
            return userEntities
        }

        fun makeUserModel(): UserModel {
            return UserModel(randomInt(), randomUuid(), randomUuid(), randomUuid(), AddressModel(randomUuid(), randomUuid(), randomUuid(), randomUuid(), GeoModel(randomUuid(), randomUuid())), randomUuid(), randomUuid(), CompanyModel(randomUuid(), randomUuid(), randomUuid()))
        }
    }
}