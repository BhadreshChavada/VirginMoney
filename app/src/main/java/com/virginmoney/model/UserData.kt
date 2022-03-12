package com.virginmoney.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserData(
    @Json(name = "createdAt")
    val createdAt: String = "",
    @Json(name = "firstName")
    val firstName: String = "",
    @Json(name = "lastName")
    val lastName: String = "",
    @Json(name = "jobtitle")
    val jobtitle: String = "",
    @Json(name = "avatar")
    val avatar: String = "",
    @Json(name = "id")
    val id: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "favouriteColor")
    val favouriteColor: String = ""
) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}