package com.virginmoney.data

import com.virginmoney.connectivity.RemoteApiService
import com.virginmoney.connectivity.ResultWrapper
import com.virginmoney.model.RoomData
import com.virginmoney.model.UserData
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val remoteApiService: RemoteApiService) {

    suspend fun getUserData(): ResultWrapper<List<UserData>> {
        return safeApiCall {
            remoteApiService.getUserData()
        }
    }

    suspend fun getRoomsData(): ResultWrapper<List<RoomData>> {
        return safeApiCall {
            remoteApiService.getRoomData()
        }
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ResultWrapper<T> =
        try {
            val response: Response<T> = apiCall()
            if (response.isSuccessful) {
                ResultWrapper.Success(response.body())
            } else {
                ResultWrapper.Error(exception = HttpException(response))
            }
        } catch (httpException: HttpException) {
            ResultWrapper.Error(exception = httpException)
        } catch (e: Exception) {
            ResultWrapper.Error(exception = e)
        }
}