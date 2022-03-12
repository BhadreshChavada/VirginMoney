package com.virginmoney.viewModel

import androidx.lifecycle.MutableLiveData
import com.virginmoney.connectivity.ResultWrapper
import com.virginmoney.data.DefaultDataSource
import com.virginmoney.model.RoomData
import com.virginmoney.model.UserData
import javax.inject.Inject

class ApiRepository
@Inject constructor(
    private val defaultDataSource: DefaultDataSource
) {

    private val _userLiveData = MutableLiveData<ResultWrapper<List<UserData>>?>()
    val userLiveData: MutableLiveData<ResultWrapper<List<UserData>>?>
        get() = _userLiveData


    private val _roomsLiveData = MutableLiveData<ResultWrapper<List<RoomData>>?>()
    val roomsLiveData: MutableLiveData<ResultWrapper<List<RoomData>>?>
        get() = _roomsLiveData

    suspend fun getUserData() {
        _userLiveData.value = defaultDataSource.getUserData()
        _userLiveData.value = null
    }

    suspend fun getRoomsData() {
        _roomsLiveData.value = defaultDataSource.getRoomsData()
        _roomsLiveData.value = null
    }
}