package com.virginmoney

import android.app.Application
import com.virginmoney.connectivity.ConnectivityStatusProvider
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class VirginMoney : Application() {

    @Inject
    lateinit var connectivityStatusProvider: ConnectivityStatusProvider


    override fun onCreate() {
        super.onCreate()
        connectivityStatusProvider.setConsumerApp(this)

    }
}