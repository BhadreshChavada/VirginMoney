package com.virginmoney.connectivity

import com.virginmoney.model.RoomData
import com.virginmoney.model.UserData
import retrofit2.Response
import retrofit2.http.GET

interface RemoteApiService {

    @GET("people")
    suspend fun getUserData(
    ): Response<List<UserData>>

    @GET("rooms")
    suspend fun getRoomData(
    ): Response<List<RoomData>>

}