package com.virginmoney.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.virginmoney.connectivity.ConnectivityStatusProvider
import com.virginmoney.connectivity.NoInternetException
import com.virginmoney.connectivity.ResultWrapper
import com.virginmoney.model.RoomData
import com.virginmoney.model.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultDataSource
@Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val connectivityStatusProvider: ConnectivityStatusProvider
) {

    suspend fun getUserData(): ResultWrapper<List<UserData>> {
        return requestRemoteDataSource {
            remoteDataSource.getUserData()
        }
    }

    suspend fun getRoomsData(): ResultWrapper<List<RoomData>> {
        return requestRemoteDataSource {
            remoteDataSource.getRoomsData()
        }
    }


    private suspend fun <T> requestRemoteDataSource(
        remoteRequest: suspend () -> ResultWrapper<T>
    ): ResultWrapper<T> {
        return if (!connectivityStatusProvider.isInternetAvailable())
            ResultWrapper.Error(exception = NoInternetException())
        else
            remoteRequest.invoke()
    }
}