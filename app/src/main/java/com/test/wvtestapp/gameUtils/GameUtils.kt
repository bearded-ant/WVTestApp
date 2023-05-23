package com.test.wvtestapp.gameUtils

import com.test.wvtestapp.ui.main.MainViewModel

class GameUtils(private val viewModel: MainViewModel) {

    private val imageRepo = ImageRepo()


    //посадить семечку
    fun plantSeeds(fieldNumber: Int) {
        viewModel.refreshFruitLevel(fieldNumber, 1)
        viewModel.refreshSeedCount(fieldNumber, -1)
    }

    //собрать урожай
    fun harvestingCrop(fieldNumber: Int) {
        viewModel.refreshFruitCount(fieldNumber, (1..5).random())
        viewModel.refreshFruitLevel(fieldNumber, 0)
    }

    //проверка грядки
    fun isReadyForPlanting(fruitLevel: Int): Boolean {
        return fruitLevel == 0
    }

    //можно собирать?
    fun canCollect(fruitLevel: Int): Boolean {
        return fruitLevel == 3
    }

    //перерисовать грядку
    fun reDrawField(fieldNumber: Int, fruitLevel: Int): Int {
        return when (fieldNumber) {
            0 -> imageRepo.melonImages.getValue(fruitLevel)
            1 -> imageRepo.pineappleImages.getValue(fruitLevel)
            2 -> imageRepo.watermelonImages.getValue(fruitLevel)
            else -> throw IllegalArgumentException("Invalid field number: $fieldNumber")
        }
    }
}