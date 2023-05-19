package com.test.wvtestapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _remoteConfigUrl = MutableLiveData<String>()
    val remoteConfigUrl: LiveData<String> = _remoteConfigUrl

    fun refreshLatestDeal(url:String) {
        viewModelScope.launch {
            _remoteConfigUrl.postValue(url)
        }
    }
}