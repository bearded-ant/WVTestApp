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

    private val _fruits = MutableLiveData<List<Fruit>>()
    val fruits: LiveData<List<Fruit>> = _fruits

    fun initFruit() {
        viewModelScope.launch {
            _fruits.postValue(repo.fruits)
        }
    }

    fun initWallet() {
        viewModelScope.launch {
            _userWallet.postValue(repo.userWallet)
        }
    }


    private val _remoteConfigUrl = MutableLiveData<String>()
    val remoteConfigUrl: LiveData<String> = _remoteConfigUrl

    fun refreshConfigUrl(url: String) {
        viewModelScope.launch {
            _remoteConfigUrl.postValue(url)
        }
    }

    private val _userWallet = MutableLiveData<Wallet>()
    val userWallet: LiveData<Wallet> = _userWallet

    fun refreshWallet(coins: Int) {
        viewModelScope.launch {
            val currentWallet = userWallet.value
            currentWallet!!.coins += coins
            _userWallet.postValue(currentWallet!!)
        }
    }


    fun refreshFruitLevel(fieldId: Int, fruitLevel: Int) {
        viewModelScope.launch {
            val currentList = fruits.value ?: emptyList()
            currentList[fieldId].fruitLevel = fruitLevel
            _fruits.value = currentList
        }
    }

    fun refreshFruitCount(fieldId: Int, fruitCount: Int) {
        viewModelScope.launch {
            val currentList = fruits.value ?: emptyList()
            currentList[fieldId].count += fruitCount
            _fruits.value = currentList
        }
    }

    fun refreshSeedCount(fieldId: Int, seedCount: Int) {
        viewModelScope.launch {
            val currentList = fruits.value ?: emptyList()
            currentList[fieldId].seedCount = currentList[fieldId].seedCount + seedCount
            _fruits.value = currentList
        }
    }

}