package com.vlabs.jsonplaceholder.remote.models

import com.squareup.moshi.Json

data class UserModel(@Json(name = "id") val userId: Int,
                     @Json(name = "name") val name: String,
                     @Json(name = "username") val userName: String,
                     @Json(name = "email") val email: String,
                     @Json(name = "address") val address: AddressModel,
                     @Json(name = "phone") val phone: String,
                     @Json(name = "website") val website: String,
                     @Json(name = "company") val company: CompanyModel)

data class AddressModel(@Json(name = "street") val street: String,
                        @Json(name = "suite") val suite: String,
                        @Json(name = "city") val city: String,
                        @Json(name = "zipcode") val zipCode: String,
                        @Json(name = "geo") val geo: GeoModel)

data class GeoModel(@Json(name = "lat") val lat: String,
                    @Json(name = "lng") val lng: String)

data class CompanyModel(@Json(name = "name") val name: String,
                        @Json(name = "catchPhrase") val catchPhrase: String,
                        @Json(name = "bs") val bs: String)