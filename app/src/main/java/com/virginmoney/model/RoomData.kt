package com.virginmoney.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoomData(
    @Json(name = "isOccupied")
    val isOccupied: Boolean = false,
    @Json(name = "createdAt")
    val createdAt: String = "",
    @Json(name = "maxOccupancy")
    val maxOccupancy: Double = 0.0,
    @Json(name = "id")
    val id: String = ""
)