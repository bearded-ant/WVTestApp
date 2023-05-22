package com.test.wvtestapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.wvtestapp.model.Fruit
import com.test.wvtestapp.model.Repo
import com.test.wvtestapp.model.Wallet
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repo: Repo = Repo()

    private val _remoteConfigUrl = MutableLiveData<String>()
    val remoteConfigUrl: LiveData<String> = _remoteConfigUrl

    fun refreshLatestDeal(url: String) {
        viewModelScope.launch {
            _remoteConfigUrl.postValue(url)
        }
    }

    private val _userWallet = MutableLiveData<Wallet>()
    val userWallet: LiveData<Wallet> = _userWallet
    fun initWallet() {
        viewModelScope.launch {
            _userWallet.postValue(repo.userWallet)
        }
    }

    private val _fruits = MutableLiveData<List<Fruit>>()
    val fruits: LiveData<List<Fruit>> = _fruits

    fun refreshFruits(fruits: List<Fruit>) {
        viewModelScope.launch {
            _fruits.postValue(fruits)
        }
    }

    private val _growing = MutableLiveData<List<Int>>()
    val growing: LiveData<List<Int>> = _growing

    fun refreshGrowingState(growingState: List<Int>) {
        viewModelScope.launch {
            _growing.postValue(growingState)
        }
    }
}