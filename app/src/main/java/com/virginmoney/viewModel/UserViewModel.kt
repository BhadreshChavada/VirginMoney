package com.virginmoney.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virginmoney.connectivity.ResultWrapper
import com.virginmoney.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    val userData: MutableLiveData<ResultWrapper<List<UserData>>?> = apiRepository.userLiveData

    init {
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch {
            apiRepository.getUserData()
        }
    }

}