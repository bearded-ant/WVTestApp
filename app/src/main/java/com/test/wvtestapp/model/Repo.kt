package com.test.wvtestapp.model

class Repo {
    val fruits: List<Fruit> = listOf(
        Fruit(name = "banana", count = 5, seedCount = 5),
        Fruit(name = "apple", count = 5, seedCount = 5),
        Fruit(name = "coco", count = 5, seedCount = 5)
    )
    val userWallet:Wallet = Wallet(coins = 50, fruits)
}