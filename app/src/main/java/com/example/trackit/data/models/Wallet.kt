package com.example.trackit.data.models

sealed class Wallet {
    data object Loading : Wallet()

    data class Loaded (
        val cards: List<Card>,
    ) : Wallet()
}