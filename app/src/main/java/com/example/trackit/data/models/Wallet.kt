package com.example.trackit.data.models

data class Wallet(
    val accounts: List<Item> = listOf(),
    val isEmpty: Boolean = true
)