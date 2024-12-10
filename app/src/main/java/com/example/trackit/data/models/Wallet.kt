package com.example.trackit.data.models

sealed class Wallet {
    data object Loading : Wallet()

    data class Loaded (
        val walletId: String,
        val ownerId: String,
        val cards: List<Card>,
    ) : Wallet()
}