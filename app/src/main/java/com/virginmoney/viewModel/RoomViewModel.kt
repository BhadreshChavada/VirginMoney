package com.virginmoney.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virginmoney.connectivity.ResultWrapper
import com.virginmoney.model.RoomData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel
@Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {


    val roomsData: MutableLiveData<ResultWrapper<List<RoomData>>?> = apiRepository.roomsLiveData

    init {
        getRoomData()
    }

    private fun getRoomData() {
        viewModelScope.launch {
            apiRepository.getRoomsData()
        }
    }
}