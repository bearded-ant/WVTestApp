package com.test.wvtestapp.model

class Repo {
    val fruits: List<Fruit> = listOf(
        Fruit(name = "pineapple", count = 5, seedCount = 5, fruitLevel = 0),
        Fruit(name = "melon", count = 5, seedCount = 5, fruitLevel = 0),
        Fruit(name = "watermelon", count = 5, seedCount = 5, fruitLevel = 0)
    )
    val userWallet:Wallet = Wallet()
}